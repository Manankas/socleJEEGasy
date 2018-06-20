#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint._config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fenonantenaina
 *
 */
@Configuration
@ComponentScan({"${package}.constraint"})
public class ConstraintConfig {

}
