#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.mapper;

/**
 * @author Fenonantenaina
 *
 * @param <TDto>
 * @param <TInfoDto>
 * @param <TEntity>
 */
public interface ToDtoDefaultConverter<TDto, TInfoDto, TEntity> extends ToDtoConverter<TDto, TEntity>, ToInfoDtoConverter<TInfoDto, TEntity> {

}
