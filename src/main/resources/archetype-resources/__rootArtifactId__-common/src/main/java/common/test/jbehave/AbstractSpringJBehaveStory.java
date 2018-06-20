#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.test.jbehave;

import java.util.Arrays;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

//@RunWith(SpringAnnotatedEmbedderRunner.class)
/**
 * @author Fenonantenaina
 *
 */
public abstract class AbstractSpringJBehaveStory extends JUnitStory {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    
	@Autowired
	private ApplicationContext applicationContext;

	protected AbstractSpringJBehaveStory(){
	    Embedder embedder = new Embedder();
	    embedder.useEmbedderControls(embedderControls());
	    embedder.useMetaFilters(Arrays.asList("-skip"));
	    useEmbedder(embedder);
	}
	
	private EmbedderControls embedderControls() {
	    return new EmbedderControls()
	            .doIgnoreFailureInView(true)
	            .useStoryTimeouts("300");
	}
	

	@Override
	public InjectableStepsFactory stepsFactory() {
	    return new SpringStepsFactory(configuration(), applicationContext);
	}

	@Override
	public Configuration configuration() {
	    return new MostUsefulConfiguration()
	            .useStoryPathResolver(storyPathResolver())
	            .useStoryLoader(storyLoader())
	            .useStoryReporterBuilder(storyReporterBuilder())
	            .useParameterControls(parameterControls());
	}
	
	private StoryPathResolver storyPathResolver() {
	    return new UnderscoredCamelCaseResolver();
	}

	private StoryLoader storyLoader() {
	    return new LoadFromClasspath();
	}

	private StoryReporterBuilder storyReporterBuilder() {
	    return new StoryReporterBuilder()
	            .withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
	            .withPathResolver(new ResolveToPackagedName())
	            .withFailureTrace(true)
	            .withDefaultFormats()
	            .withFormats(Format.CONSOLE, Format.HTML);
	}
	private ParameterControls parameterControls() {
	    return new ParameterControls()
	            .useDelimiterNamedParameters(true);
	}


}
