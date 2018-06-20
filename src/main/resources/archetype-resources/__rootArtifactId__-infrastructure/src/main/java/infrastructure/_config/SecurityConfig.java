#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure._config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import ${package}.service.metier.security.UserSecuritySM;

/**
 * @author Fenonantenaina
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled = true ) //activer @Secured + @PreAuthorize au niveau IService
class SecurityConfig {

	@Autowired
    private UserSecuritySM userSecuritySM;
	
    @Autowired
    private Environment env;
       
    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", userSecuritySM);
    }
    
    @Bean
    public OAuth2AccessDeniedHandler accessDeniedHandler() {
    	return new OAuth2AccessDeniedHandler();
    }
    
    @Bean
    public RoleVoter roleVoter(){
    	RoleHierarchyVoter roleVoter = new RoleHierarchyVoter(roleHierarchy());
    	return roleVoter;
    }

    @Bean
    public RoleHierarchy roleHierarchy(){
    	RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    	roleHierarchy.setHierarchy(env.getProperty("application.security.rolesHierarchy"));
    	return roleHierarchy;
    }
    
    
    @Bean(name="defaultWebSecurityExpressionHandler")
    public SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return defaultWebSecurityExpressionHandler;
    }
    
    /**
     * Configuration sécurité sur MVC
     * @author Fenonantenaina
     *
     */
    @Configuration
    public static class MVCSecurityConfig extends WebSecurityConfigurerAdapter{
    	
    	@Autowired
    	private SecurityConfig securityConfig;

        @Autowired
        private Environment env;
        
    	protected void configure(HttpSecurity http) throws Exception {
        	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry interceptUrl = 
        			http.authorizeRequests()
    			        		.expressionHandler(securityConfig.webExpressionHandler())
    		                    .antMatchers(
    		                            "/",
    		                            "/js/**", 
    		                            "/images/**", 
    		                            "/fonts/**",
    		                            "/css/**", 
    		                            "/webjars/**", 
    		                            "/favicon.ico", 
    		                            "/signup",
    			                		"/configuration/ui",
    		                            "/configuration/security",
    		                            "/v2/api-docs",
    		                            "/validatorUrl/**",
    			                		"/swagger-ui.html",
    		                            "/swagger-resources/**",
    		                            "/swagger**").permitAll()				//pour tout le monde
    		                    .antMatchers("__security__").denyAll()
    			                .antMatchers(env.getProperty("application.security.admin.pattern")).hasRole("ADMIN")	//pour ADMIN
    			                .anyRequest().authenticated();
        	
            // AUTRE CONFIGURATION
        	interceptUrl.and().formLogin()
    			                .loginPage("/signin")
    			                .permitAll()
    			                .failureUrl("/signin?error=1")
    			                .loginProcessingUrl("/authenticate")
    			                .permitAll()
    			                .and()
    			            .logout()
    			                .logoutUrl("/logout")
    			                .permitAll()
    			                .logoutSuccessUrl("/signin?logout")
    			                .and()
    			            .rememberMe()
    			                .rememberMeServices(securityConfig.rememberMeServices())
    			                .key("remember-me-key");
    	}
    }
    
    /**
     * Configuration securité sur Rest Api
     * @author Fenonantenaina
     *
     */
    @Configuration
    @Order(1)
    public static class WebApiSecurityConfig extends WebSecurityConfigurerAdapter {
        
        @Autowired
        private PasswordEncoder passwordEncoder;
        
    	@Autowired
        private UserSecuritySM userSecuritySM;
    	
    	@Autowired
        private OAuth2AccessDeniedHandler accessDeniedHandler;
        
        @Bean(name="authenticationManager") @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.eraseCredentials(true)
                .userDetailsService(userSecuritySM)
                .passwordEncoder(passwordEncoder);
        }
        
    	protected void configure(HttpSecurity http) throws Exception {
    		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry interceptUrl = 
    			http.requestMatcher(EndpointRequest.to("api","oauth"))
    				.csrf().disable()
    				.authorizeRequests()
    				.antMatchers("/oauth/token").permitAll()
    				.antMatchers("/api/guest/**").permitAll()
    				.anyRequest()
    				.authenticated();

            interceptUrl.and()
            			.exceptionHandling()
            			.accessDeniedHandler(accessDeniedHandler);
    	}
    }

}