#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.operations;

/**
 * @author Fenonantenaina
 *
 */
public enum OperationFlags {

	NO_OPERATION(0),
	OPERATION_FAILED(-1),
	OPERATION_SUCCESS(1);
	
	private final int value;
	
	OperationFlags(int val){
		value = val;
	}
	
	public int getValue(){return this.value;}

}
