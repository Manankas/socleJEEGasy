#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.applicatif;

import java.io.Serializable;

import ${package}.common.service.operations.OperationFlags;


/**
 * @author Fenonantenaina
 *
 */
public interface GenericSA<TDto, TId extends Serializable> extends RetrieveSA<TDto> {
	
	public TDto findById(TId id);
		
	public OperationFlags insert(TDto dto);
	
	public OperationFlags update(TDto dto);
	
	public OperationFlags delete(TId id);
	
}
