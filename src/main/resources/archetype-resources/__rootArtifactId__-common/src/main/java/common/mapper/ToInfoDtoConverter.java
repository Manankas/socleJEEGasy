#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.mapper;

import java.util.Collection;

/**
 * @author Fenonantenaina
 *
 * @param <TEntity>
 * @param <TDto>
 */
public interface ToInfoDtoConverter<TInfoDto, TEntity> {

	public TInfoDto convertToInfoDto(TEntity source);
	
	public Collection<TInfoDto> convertToInfoDto(Collection<TEntity> sources);
	
}
