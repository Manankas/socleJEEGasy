#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${package}.common.service.security.AbstractUserServiceImpl;
import ${package}.data.entites.security.Authority;
import ${package}.data.entites.security.Utilisateur;
import ${package}.repository.security.authority.AuthorityRepository;
import ${package}.repository.security.user.UtilisateurRepository;

/**
 * @author Fenonantenaina
 *
 */
@Transactional
@Service("userSecuritySM")
public class UserSecuritySMImpl extends AbstractUserServiceImpl<Utilisateur> implements UserSecuritySM {

	@Autowired
	private UtilisateurRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	protected Utilisateur findUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	protected Collection<GrantedAuthority> getAuthorities(Utilisateur userInfo) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Collection<Authority> userAuths = this.authorityRepository.findAllByUserUsername(userInfo.getUsername());
		for (Authority authority : userAuths) {
			authorities.add(new SimpleGrantedAuthority(authority.getAuthority().startsWith("ROLE_") ? authority.getAuthority() : "ROLE_" + authority.getAuthority()));
		}
		return authorities;
	}

}
