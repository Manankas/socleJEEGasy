#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint.converter.adresses;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import ${package}.common.mapper.ToDtoConverter;
import ${package}.common.mapper.ToEntityConverter;
import ${package}.data.dto.json.adresses.AdresseDto;
import ${package}.data.entites.Adresse;

@Mapper(
		componentModel="spring",
		unmappedTargetPolicy=ReportingPolicy.IGNORE
	)
public interface AdresseConverter extends ToEntityConverter<Adresse, AdresseDto>, ToDtoConverter<AdresseDto, Adresse> {

	@Override
	@Mapping(target="personnes", ignore=true)
	public Adresse merge(Adresse source,@MappingTarget Adresse dest);
	
}
