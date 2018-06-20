#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.test.jbehave;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterControls;
import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;


/**
 * @author Fenonantenaina
 *
 * @param <TStories>
 */
public abstract class AbstractSpringJBehaveStories<TStories extends AbstractSpringJBehaveStories<TStories>> extends JUnitStories{

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    
	@Autowired
	private ApplicationContext applicationContext;
	
	private final CrossReference xref = new CrossReference();
	
	private Class<TStories>  entityType= null;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void initialize() {
	    Type type = this.getClass().getGenericSuperclass();
	    ParameterizedType paramType = null;
	    while (paramType == null) {
	        if ((type instanceof ParameterizedType)) {
	        	paramType = (ParameterizedType) type;
	        } else {
	        	type = ((Class<?>) type).getGenericSuperclass();
	        }
	    }
	    entityType = (Class<TStories>) paramType.getActualTypeArguments()[0];
	}

	protected List<String> storyPaths() {
        URL searchInURL = CodeLocations.codeLocationFromClass(this.entityType);
        return new StoryFinder().findPaths(searchInURL, "**/*.story", "");
	}

	
	protected AbstractSpringJBehaveStories(){
	    Embedder embedder = new Embedder();
	    embedder.useEmbedderControls(embedderControls());
	    embedder.useMetaFilters(Arrays.asList("-skip"));
	    useEmbedder(embedder);
	}
	
	private EmbedderControls embedderControls() {
	    return configuredEmbedder().embedderControls()
				.doGenerateViewAfterStories(true)
				.doIgnoreFailureInStories(false)
				.doIgnoreFailureInView(true)
				.doVerboseFailures(true)
	            .useStoryTimeouts("300");
	}
	
//	@Override
//	public InjectableStepsFactory stepsFactory() {
//		return new SpringStepsFactory(configuration(), applicationContext);
//	}
	
	@Override
	public Configuration configuration() {
	    return new MostUsefulConfiguration()
	            .useStoryPathResolver(storyPathResolver())
	            .useStoryLoader(storyLoader())
	            .useStepMonitor(xref.getStepMonitor())
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
	            .withFormats(Format.CONSOLE,Format.HTML);
	}
	private ParameterControls parameterControls() {
	    return new ParameterControls()
	            .useDelimiterNamedParameters(true);
	}



    //protected abstract ApplicationContext loadApplicationContext();
}
