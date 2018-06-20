#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint.factory.dto.personnes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${package}.constraint.converter.personnes.PersonneConverter;
import ${package}.data.dto.json.personnes.PersonneDto;
import ${package}.data.entites.Personne;

/**
 * @author Fenonantenaina
 *
 */
@Component("personneDtoFactory")
public class PersonneDtoFactoryImpl implements PersonneDtoFactory {

	@Autowired
	private PersonneConverter personneConverter;
	
	@Override
	public PersonneDto getInstance() {
		return new PersonneDto();
	}

	@Override
	public PersonneDto getInstance(Personne personne) {
		return personneConverter.convertToDto(personne);
	}

}
