#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.test.serenity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import net.serenitybdd.jbehave.SerenityStories;
import net.serenitybdd.junit.spring.integration.SpringIntegrationSerenityRunner;

/**
 * @author Fenonantenaina
 *
 */
@RunWith(SpringIntegrationSerenityRunner.class)
public abstract class AbstractSpringSerenityStories<TStories extends AbstractSpringSerenityStories<TStories>> extends SerenityStories {
	
    //@Managed(driver="firefox", uniqueSession = true)                              
    //WebDriver driver;
    
//    @ClassRule
//    public static final SpringIntegrationClassRule SPRING_CLASS_RULE = new SpringIntegrationClassRule();
//
//    @Rule
//    public final SpringIntegrationMethodRule springMethodRule = new SpringIntegrationMethodRule();

    
	@Autowired
	private ApplicationContext applicationContext;
	
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
	
	protected AbstractSpringSerenityStories(){
		super();
	}
	

	@Override
	public InjectableStepsFactory stepsFactory() {
	    return new SpringStepsFactory(configuration(), applicationContext);
	}
	
}
