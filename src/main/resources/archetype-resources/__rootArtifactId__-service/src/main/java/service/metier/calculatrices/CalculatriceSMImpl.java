#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.calculatrices;

import org.springframework.stereotype.Service;

/**
 * @author Fenonantenaina
 *
 */
@Service("calculatriceSM")
public class CalculatriceSMImpl implements CalculatriceSM {

	@Override
	public int ajouter(int a, int b) {
		return a + b;
	}

	
	@Override
	public int multiplier(int a, int b) {
		return a * b;
	}


	@Override
	public int soustraire(int a, int b) {
		return a - b;
	}


	@Override
	public int diviser(int a, int b) {
		return a/b;
	}

}
