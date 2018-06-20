#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;

import ${package}.infrastructure._config.ApplicationConfig;

//@EnableWebMvc
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@CrossOrigin(value={"/api/**","/oauth/**"})
@Import(value= {ApplicationConfig.class})
public class ApplicationBootstrap extends SpringBootServletInitializer{

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureAppliction(builder);
	}

	
	public static void main(String[] args) {
		configureAppliction(new SpringApplicationBuilder()).run(args);
	}
	
	private static SpringApplicationBuilder configureAppliction(SpringApplicationBuilder builder) {
		return builder.bannerMode(Banner.Mode.CONSOLE)
				.sources(ApplicationBootstrap.class);
	}
//	
//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.addUrlMappings("/","/api");
//        return registration;
//    }

}
