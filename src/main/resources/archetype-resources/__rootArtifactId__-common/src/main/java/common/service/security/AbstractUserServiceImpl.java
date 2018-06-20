#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Fenonantenaina
 *
 * @param <TUser>
 */
@Transactional
public abstract class AbstractUserServiceImpl<TUser extends UserInfo> implements AbstractUserService<TUser> {

	@Override
	public UserDetails loadUserByUsername(String username)	throws UsernameNotFoundException {
		TUser account = this.findUserByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}

	@Override
	public void signin(TUser account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private User createUser(TUser userInfo) {
		return new User(userInfo.getUsername(), userInfo.getPassword(), getAuthorities(userInfo));
	}
	
	private Authentication authenticate(TUser userInfo) {
		return new UsernamePasswordAuthenticationToken(createUser(userInfo), null, getAuthorities(userInfo));		
	}
	
	/**
	 * Récupération information sur l'utilisateur à partir de son username
	 * @param username
	 * @return
	 */
	protected abstract TUser findUserByUsername(String username);
	
	/**
	 * Les roles correspondant à l'utilisateur courant
	 * @param userInfo
	 * @return
	 */
	protected abstract Collection<GrantedAuthority> getAuthorities(TUser userInfo);
}
