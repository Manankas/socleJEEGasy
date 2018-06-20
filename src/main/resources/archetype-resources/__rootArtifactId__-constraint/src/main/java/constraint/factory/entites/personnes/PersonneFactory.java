#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint.factory.entites.personnes;

import ${package}.data.dto.json.personnes.PersonneDto;
import ${package}.data.entites.Personne;

/**
 * @author Fenonantenaina
 *
 */
public interface PersonneFactory {

	public Personne getInstance();
	
	public Personne getInstance(PersonneDto dto);
	
}
