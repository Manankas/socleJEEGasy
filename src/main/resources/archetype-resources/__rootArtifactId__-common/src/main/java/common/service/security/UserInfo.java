#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.security;

/**
 * @author Fenonantenaina
 *
 */
public interface UserInfo {

	public String getUsername();
	
	public String getPassword();		
}
