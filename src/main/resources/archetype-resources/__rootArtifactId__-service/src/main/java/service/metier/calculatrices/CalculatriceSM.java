#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.calculatrices;


/**
 * @author Fenonantenaina
 *
 */
public interface CalculatriceSM {

	int ajouter(int a, int b);
	
	int soustraire(int a, int b);
	
	int multiplier(int a, int b);
	
	int diviser(int a, int b);
}
