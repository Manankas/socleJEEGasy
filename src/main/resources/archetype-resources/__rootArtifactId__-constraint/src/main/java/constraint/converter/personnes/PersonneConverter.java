#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint.converter.personnes;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ${package}.common.mapper.ToDtoConverter;
import ${package}.common.mapper.ToEntityConverter;
import ${package}.constraint.converter.adresses.AdresseConverter;
import ${package}.data.dto.json.personnes.PersonneDto;
import ${package}.data.entites.Personne;

/**
 * @author Fenonantenaina
 *
 */
@Mapper(
		componentModel="spring",
		unmappedTargetPolicy=ReportingPolicy.IGNORE,
		uses={AdresseConverter.class}
	)
public interface PersonneConverter extends ToEntityConverter<Personne, PersonneDto>, ToDtoConverter<PersonneDto, Personne> {

	@Override
	@Mapping(source="adresse.id", target="adresseId")
	public Personne convertFromDto(PersonneDto dto);
	
}
