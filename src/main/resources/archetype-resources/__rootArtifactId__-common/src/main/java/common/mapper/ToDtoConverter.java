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
public interface ToDtoConverter<TDto, TEntity> {

	public TDto convertToDto(TEntity source);
	
	public Collection<TDto> convertToDto(Collection<TEntity> sources);
	
}
