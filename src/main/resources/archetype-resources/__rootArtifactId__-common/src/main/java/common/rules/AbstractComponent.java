#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.rules;

/**
 * @author Fenonantenaina
 *
 * @param <TObject>
 */
public abstract class AbstractComponent<TObject extends Object> {
	
	public abstract void execute(TObject object) throws Exception;
	
}
