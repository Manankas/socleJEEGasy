#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure._config;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Fenonantenaina
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    
	@Autowired
	private MessageSource messageSource;
	
    @Bean
    public Docket apiDocket() { 
        return new Docket(DocumentationType.SWAGGER_2)  
                .select()                                  
                .apis(RequestHandlerSelectors.basePackage("${package}.infrastructure.api"))
                //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()) 
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(true)
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(newArrayList(securityContext()))
                .enableUrlTemplating(false);
                /*
                 * security config
                 */                                           
    }
    
    private ApiInfo apiInfo() {
    	String title = messageSource.getMessage("label.documentation.title", null, null, null);
    	String descr = messageSource.getMessage("label.documentation.description", null, null, null);
    	String version = messageSource.getMessage("label.documentation.version", null, null, null);
    	String contactNom = messageSource.getMessage("label.documentation.contact.nom", null, null, null);
    	String contactUrl = messageSource.getMessage("label.documentation.contact.url", null, null, null);
    	String contactEmail = messageSource.getMessage("label.documentation.contact.email", null, null, null);

    	
        ApiInfo apiInfo = new ApiInfo(title, descr, version, "Terms of service", new Contact(contactNom, contactUrl, contactEmail), "License of API", "API license URL", Collections.emptyList());
        		//new ApiInfo(title, descr, version, null, new Contact(contactNom, contactUrl, contactEmail), null, null);
        return apiInfo;
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build();
      }
    
    
    @Bean
    public SecurityConfiguration security() {
    	return SecurityConfigurationBuilder.builder()
    	        .clientId("${parentArtifactId}-client")
    	        .clientSecret("pAdmin123")
    	        .scopeSeparator(" ")
    	        .useBasicAuthenticationWithAccessCodeGrant(true)
    	        .build();    	
    }
    
    
    
    List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = newArrayList(
        		new AuthorizationScope("read", "accessEverything 1"),
        		new AuthorizationScope("write", "accessEverything 2"),
        		new AuthorizationScope("trust", "accessEverything 3")
        ).toArray(new AuthorizationScope[]{});
        return newArrayList(
            new SecurityReference("Authorization", authorizationScopes));
      }
    
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }
}
