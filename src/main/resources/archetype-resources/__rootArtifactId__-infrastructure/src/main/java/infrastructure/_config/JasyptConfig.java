#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure._config;

import org.springframework.context.annotation.Configuration;

//@EnableEncryptableProperties
@Configuration
public class JasyptConfig {

//	@Bean
//	public PersonneDto myTestBean(
//			@Value("${symbol_dollar}{spring.datasource.password}") String bddPass,
//			@Value("${symbol_dollar}{application.bdd-encryptor.algorithm}") String jasyptAlgo, 
//			@Value("${symbol_dollar}{application.bdd-encryptor.password}") String jasyptPass) {
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.println("============================================");
//		System.out.format("My bddPass variable is: %s${symbol_escape}n", bddPass);
//		System.out.format("My jasyptAlgo variable is: %s${symbol_escape}n", jasyptAlgo);
//		System.out.format("My jasyptPass variable is: %s${symbol_escape}n", jasyptPass);
//		System.out.println("============================================");
//		return new PersonneDto();
//	}
}
