#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.applicatif;

import java.util.Collection;

public interface RetrieveSA<TDto> {

	public Collection<TDto> findAll();
	
	public Collection<TDto> findAll(final int page, final int rowPerPage);

	public long count();
}
