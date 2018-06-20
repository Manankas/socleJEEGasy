#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository.security.user;

import ${package}.common.repository.GenericJpaRepository;
import ${package}.data.entites.security.Utilisateur;

/**
 * @author Fenonantenaina
 *
 */
public interface UtilisateurRepository  extends GenericJpaRepository<Utilisateur,String> {

	public Utilisateur findByUsername(String username);
	
}
