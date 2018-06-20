#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common._config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fenonantenaina
 *
 */
@Configuration
@ComponentScan({"${package}.common"})
public class CommonConfig {
	
}
