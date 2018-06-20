#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.security;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Fenonantenaina
 *
 */
public interface AbstractUserService<TUser extends UserInfo> extends UserDetailsService {

	public void signin(TUser account);
	
}
