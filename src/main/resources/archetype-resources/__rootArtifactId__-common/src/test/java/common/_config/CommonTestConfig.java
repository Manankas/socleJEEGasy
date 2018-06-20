#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common._config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Fenonantenaina
 *
 */
@Profile("tests")
@Configuration
@ComponentScan({"${package}.common"})
public class CommonTestConfig {

	
}
