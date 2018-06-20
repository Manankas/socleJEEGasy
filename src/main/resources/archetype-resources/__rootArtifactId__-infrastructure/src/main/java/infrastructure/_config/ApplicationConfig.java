#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure._config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ${package}.common._config.CommonConfig;
import ${package}.constraint._config.ConstraintConfig;
import ${package}.data._config.DomainConfig;
import ${package}.repository._config.JPARepositoryConfig;
import ${package}.service._config.ServiceApplicatifConfig;
import ${package}.service._config.ServiceMetierConfig;

/**
 * @author Fenonantenaina
 *
 */
@Configuration
@Import({	CommonConfig.class, DomainConfig.class, ConstraintConfig.class,
			JPARepositoryConfig.class, ServiceMetierConfig.class, 
			ServiceApplicatifConfig.class,
			OAuth2ResourceServerConfig.class,
			OAuth2AuthorizationServerConfig.class, 
			OAuth2MethodSecurityConfig.class,
			SecurityConfig.class,
			SwaggerConfig.class,
			JasyptConfig.class
	})
@EnableScheduling
@PropertySource({"classpath:version.properties"})
@ComponentScan("${package}.infrastructure._database")
public class ApplicationConfig {
	
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	DelegatingPasswordEncoder passwordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
        return passwordEncoder;
	}

}
