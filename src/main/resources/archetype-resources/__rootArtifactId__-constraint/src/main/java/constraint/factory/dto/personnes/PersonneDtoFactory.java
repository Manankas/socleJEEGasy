#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint.factory.dto.personnes;

import ${package}.data.dto.json.personnes.PersonneDto;
import ${package}.data.entites.Personne;

/**
 * @author Fenonantenaina
 *
 */
public interface PersonneDtoFactory {

	public PersonneDto getInstance();
	
	public PersonneDto getInstance(Personne personne);
	
}
