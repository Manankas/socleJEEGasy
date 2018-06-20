#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.adresses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import ${package}.common.test.junit.SpringSerenityUnitTest;
import ${package}.data.entites.Adresse;
import ${package}.service._config.metier.AcceptanceTestServiceMetierConfig;

/**
 * @author Fenonantenaina
 *
 */
@ContextConfiguration(classes=AcceptanceTestServiceMetierConfig.class)
public class AdresseSMTest extends SpringSerenityUnitTest{

	@Autowired
	private AdresseSM adresseSM;
	
	private int idAdresse = 0;
	
	@Before
	public void setUpTest(){
		idAdresse = 1;
	}
	
	@Test
	public void recupererUneAdresseParSonId(){
		Adresse adresse = this.adresseSM.findById(idAdresse);
		Assert.assertNotNull(adresse);
	}
}
