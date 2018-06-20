#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.service.metier.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author Fenonantenaina
 *
 */
public class CustomTokenEnhancerSMImpl implements CustomTokenEnhancerSM {
	
	private Map<String,Object> additionalInfo;
	
	
	public Map<String, Object> getAdditionalInfo() {
		if(additionalInfo == null) additionalInfo = new HashMap<>();
		return additionalInfo;
	}


	public void setAdditionalInfo(Map<String, Object> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}


	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,	OAuth2Authentication authentication) {        
        List<GrantedAuthority> authorities= (List<GrantedAuthority>)  authentication.getAuthorities();
        getAdditionalInfo().put("authorities", authorities);
        
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(getAdditionalInfo());
        return accessToken;
	}

}
