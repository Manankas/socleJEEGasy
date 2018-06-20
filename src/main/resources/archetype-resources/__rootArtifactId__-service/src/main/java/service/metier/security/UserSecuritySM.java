#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.security;

import ${package}.common.service.security.AbstractUserService;
import ${package}.data.entites.security.Utilisateur;

/**
 * @author Fenonantenaina
 *
 */
public interface UserSecuritySM extends AbstractUserService<Utilisateur> {

}
