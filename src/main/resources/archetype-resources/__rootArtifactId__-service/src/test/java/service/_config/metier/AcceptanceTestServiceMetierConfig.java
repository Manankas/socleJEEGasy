#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service._config.metier;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import ${package}.common._config.CommonConfig;
import ${package}.service._config.ServiceMetierConfig;

/**
 * @author Fenonantenaina
 *
 */
@TestConfiguration
@Import({CommonConfig.class, RepositoryMockConfig.class, ServiceMetierConfig.class})
@ComponentScan("${package}.service.metier")
public class AcceptanceTestServiceMetierConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    


}
