#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint.factory.filariane;

import ${package}.common.web.filariane.FilAriane;

/**
 * @author Fenonantenaina
 *
 */
public interface FilArianeFactory {

	public FilAriane homeFilAriane();
	
	public FilAriane dashboardFilAriane();
	
}
