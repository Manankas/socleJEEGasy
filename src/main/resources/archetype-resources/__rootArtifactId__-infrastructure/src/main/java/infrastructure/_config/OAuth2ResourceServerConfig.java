#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure._config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Fenonantenaina
 *
 */
@Configuration
@EnableResourceServer
@Order(3)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	public static final String RESOURCE_API_ID = "${parentArtifactId}-rest-api";
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private DefaultTokenServices tokenService;
	
	@Autowired
	private OAuth2AccessDeniedHandler accessDeniedHandler;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_API_ID)
				.stateless(false)
				.tokenServices(tokenService)
				.accessDeniedHandler(accessDeniedHandler)
				.tokenStore(tokenStore);
	}
	
	 
	 @Override
	 public void configure(HttpSecurity http) throws Exception {
		 //TokenEndpointAuthenticationFilter filter = new TokenEndpointAuthenticationFilter(authenticationManager, oAuth2RequestFactory)
		 http.requestMatchers()
			.antMatchers("/api/**")
			.and() 
			.authorizeRequests()
			.antMatchers("/api/guest/**").permitAll()
			.antMatchers("/oauth/token").permitAll()
		 	.anyRequest().authenticated()
		 	.and()
		 	.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	 }

}
