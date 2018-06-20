#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.mapper;

import org.mapstruct.MappingTarget;


/**
 * @author Fenonantenaina
 *
 * @param <TEntity>
 * @param <TDto>
 */
public interface ToEntityConverter<TEntity, TDto> {

	public TEntity convertFromDto(TDto dto);
	
	public TEntity merge(TEntity source, @MappingTarget TEntity dest);
	
}
