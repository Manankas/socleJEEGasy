#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure._config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import ${package}.data.properties.JwtSigningProperty;
import ${package}.data.properties.OauthClientProperty;
import ${package}.service.metier.security.CustomTokenEnhancerSMImpl;
import ${package}.service.metier.security.UserSecuritySM;

/**
 * @author Fenonantenaina
 *
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private static String REALM="REALM";
	
    @Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
    private UserSecuritySM userSecuritySM;
	
	@Autowired
	private OAuth2AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
    @Autowired
    private Environment env;
    
    @Autowired
    private JwtSigningProperty jwtSigningProperty;
    
    @Autowired
    private OauthClientProperty oauthClientProperty;
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
 
    	int tokenValidity = env.getProperty("application.oauth2.access.token.validity.second",int.class,60*60);
    	int refreshValidity = env.getProperty("application.oauth2.refresh.token.validity.second",int.class,60*60*2);
    	
        System.out.println("====================OauthClientProperty=====================");
        System.out.format("==> CLIENT : %s${symbol_escape}n", oauthClientProperty.getClientId());
        System.out.format("==> SECRET : %s${symbol_escape}n", oauthClientProperty.getSecretKey());
        System.out.println("============================================");
    	
        clients.inMemory()
            .withClient(oauthClientProperty.getClientId())
            .secret(oauthClientProperty.getSecretKey()) //${parentArtifactId}-client-123 -- pAdmin123 : ${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}s547ru5/ITwqY7/8s1x8beoQsjDDHRFndBC3TyXHUyrlhUOa1NcBW
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .autoApprove(true)
            .accessTokenValiditySeconds(tokenValidity)//Access token is only valid for 2 minutes.
            .refreshTokenValiditySeconds(refreshValidity)//Refresh token is only valid for 10 minutes.
            .resourceIds(OAuth2ResourceServerConfig.RESOURCE_API_ID);
    }
 
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
     
        endpoints.tokenStore(tokenStore())
                 .tokenEnhancer(tokenEnhancerChain)
                 .authenticationManager(authenticationManager)
                 .userDetailsService(userSecuritySM);
          
    }
 
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //oauthServer.realm(REALM+"/client");
    	oauthServer.tokenKeyAccess("permitAll()")
    				.checkTokenAccess("isAuthenticated()")
    				.accessDeniedHandler(accessDeniedHandler);
    }
    
    @Bean
    public TokenStore tokenStore(){
    	return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
    	KeyStoreKeyFactory keyStoreFactory = new KeyStoreKeyFactory(new ClassPathResource(jwtSigningProperty.getJksFile()), jwtSigningProperty.getPassword().toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreFactory.getKeyPair(jwtSigningProperty.getAlias()));
        //set both keypair and verifier key because converter used by authorization and resource server
        converter.setVerifierKey(getPublicKey());
        return converter;
    }
    
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        String signingKey ="123";
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(signingKey);
//        return converter;
//    }
    
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(tokenEnhancer());
        return defaultTokenServices;
    }
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancerSMImpl();
    }
    
    private String getPublicKey() {
        Resource resource = new ClassPathResource(jwtSigningProperty.getPublicKey());
        String publicKey = null;
        try {
            publicKey = IOUtils.toString(resource.getInputStream(), Charset.forName("utf8"));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return publicKey;
    }
    
}
