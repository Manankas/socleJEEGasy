#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint.factory.entites.personnes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${package}.constraint.converter.personnes.PersonneConverter;
import ${package}.data.dto.json.personnes.PersonneDto;
import ${package}.data.entites.Personne;

/**
 * @author Fenonantenaina
 *
 */
@Component("personneFactory")
public class PersonneFactoryImpl implements PersonneFactory {

	@Autowired
	private PersonneConverter personneConverter;
	
	@Override
	public Personne getInstance() {
		return new Personne();
	}

	@Override
	public Personne getInstance(PersonneDto dto) {
		return this.personneConverter.convertFromDto(dto);
	}

	
	
}
