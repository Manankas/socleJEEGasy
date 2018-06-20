#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service._config.applicatif;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import ${package}.common._config.CommonConfig;
import ${package}.constraint._config.ConstraintConfig;
import ${package}.service._config.ServiceApplicatifConfig;

/**
 * @author Fenonantenaina
 *
 */
@TestConfiguration
@Import({CommonConfig.class, ServiceMetierMockConfig.class, ConstraintConfig.class, ServiceApplicatifConfig.class})
@ComponentScan("${package}.service.applicatif")
public class AcceptanceTestServiceApplicatifConfig {

}
