#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.test.serenity;

import org.jbehave.core.reporters.Format;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import net.serenitybdd.jbehave.SerenityStory;
import net.serenitybdd.junit.spring.integration.SpringIntegrationSerenityRunner;

/**
 * @author Fenonantenaina
 *
 */
@RunWith(SpringIntegrationSerenityRunner.class)
public abstract class AbstractSpringSerenityStory extends SerenityStory {
	
    //@Managed(driver="firefox", uniqueSession = true)                              
    //WebDriver driver;
//    
//    @ClassRule
//    public static final SpringIntegrationClassRule SPRING_CLASS_RULE = new SpringIntegrationClassRule();
//
//    @Rule
//    public final SpringIntegrationMethodRule springMethodRule = new SpringIntegrationMethodRule();

    
	@Autowired
	private ApplicationContext applicationContext;
	
	protected AbstractSpringSerenityStory(){
		super();
		this.useFormats(Format.CONSOLE, Format.HTML);
	}
	

	@Override
	public InjectableStepsFactory stepsFactory() {
	    return new SpringStepsFactory(configuration(), applicationContext);
	}
		
	/*
	@Test
	@Override
    public void run() throws Throwable {        
        Embedder embedder = configuredEmbedder();
        StoryPathResolver pathResolver = embedder.configuration().storyPathResolver();
        String storyPath = pathResolver.resolve(this.getClass());
        try {
            embedder.runStoriesAsPaths(asList(storyPath));
        } finally {
            embedder.generateCrossReference();
        }
    }*/
}
