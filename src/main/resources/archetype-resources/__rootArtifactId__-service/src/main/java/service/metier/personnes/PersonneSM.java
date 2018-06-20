#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.personnes;

import java.util.Collection;

import ${package}.common.service.metier.GenericSM;
import ${package}.data.entites.Personne;

/**
 * @author Fenonantenaina
 *
 */
public interface PersonneSM extends GenericSM<Personne,Integer> {

	public Collection<Personne> findAllByName(String nomPrenom);
	
}
