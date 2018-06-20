#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service._config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Fenonantenaina
 *
 */
@Configuration
@ComponentScan("${package}.service.metier")
public class ServiceMetierConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//    	DelegatingPasswordEncoder passwordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    	passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
//        return passwordEncoder;
//	}
}
