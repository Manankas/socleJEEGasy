#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.service.applicatif.personnes;

import java.util.Collection;

import ${package}.common.service.applicatif.GenericSA;
import ${package}.data.dto.json.personnes.PersonneDto;

/**
 * @author Fenonantenaina
 *
 */
public interface PersonneSA extends GenericSA<PersonneDto,Integer> {

	public Collection<PersonneDto> findAllByName(String name);
	
}
