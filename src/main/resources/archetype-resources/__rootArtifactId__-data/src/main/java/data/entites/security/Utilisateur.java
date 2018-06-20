#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.entites.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ${package}.common.databases.typedefs.TypeDefSaltedEncryptedString;
import ${package}.common.service.security.UserInfo;

/**
 * @author Fenonantenaina
 *
 */
@Entity
@Table(name = "users")
public class Utilisateur implements UserInfo {

    @Id
    @Column(length=100, nullable = false, unique = true)
	private String username;
	
	@Column(length=100)
	private String password;
	
	@Type(type=TypeDefSaltedEncryptedString.TYPE_ENCRYPTED_STRING)
	private String email;
	
	private Boolean enabled;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
