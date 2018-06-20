#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.test.junit;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.spring.integration.SpringIntegrationClassRule;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.serenitybdd.junit.spring.integration.SpringIntegrationSerenityRunner;

/**
 * @author Fenonantenaina
 *
 */
@RunWith(SpringIntegrationSerenityRunner.class)
public abstract class SpringSerenityUnitTest{

    //@Managed(driver="firefox", uniqueSession = true)                              
    //WebDriver driver;
    
    @ClassRule
    public static final SpringIntegrationClassRule  SPRING_CLASS_RULE = new SpringIntegrationClassRule();

    @Rule
    public final SpringIntegrationMethodRule springMethodRule = new SpringIntegrationMethodRule();
    
}
