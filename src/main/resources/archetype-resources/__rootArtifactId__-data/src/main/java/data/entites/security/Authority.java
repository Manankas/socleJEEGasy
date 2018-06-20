#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.entites.security;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Fenonantenaina
 *
 */
@Entity
@Table(name = "authorities")
public class Authority {

	@EmbeddedId
	private AuthorityId id;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="username",updatable=false, insertable=false)
    private Utilisateur user;

	public AuthorityId getId() {
		return id;
	}

	public void setId(AuthorityId id) {
		this.id = id;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}		
	
	public String getUsername(){
		if(this.id==null) return null;
		return this.id.getUsername();
	}
	
	public String getAuthority(){
		if(this.id==null) return null;
		return this.id.getAuthority();
	}
}
