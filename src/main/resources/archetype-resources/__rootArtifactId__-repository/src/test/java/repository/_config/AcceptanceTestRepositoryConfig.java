#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository._config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

import ${package}.data._config.DomainConfig;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


/**
 * @author Fenonantenaina
 *
 */
@TestConfiguration
@EnableEncryptableProperties
@Import(value= {DomainConfig.class, JPARepositoryConfig.class})
@EnableAutoConfiguration
public class AcceptanceTestRepositoryConfig {

	

}
