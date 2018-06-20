#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Fenonantenaina
 *
 */
@Component
@ConfigurationProperties(prefix="application.oauth2.jwt")
public class JwtSigningProperty {

	private String jksFile;

    private String  password;
    
    private String publicKey;
    
    private String alias;

	public String getJksFile() {
		return jksFile;
	}

	public void setJksFile(String jksFile) {
		this.jksFile = jksFile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
