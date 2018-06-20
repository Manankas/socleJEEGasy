#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.personnes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsNull.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import ${package}.common.test.junit.SpringSerenityUnitTest;
import ${package}.data.entites.Personne;
import ${package}.service._config.metier.AcceptanceTestServiceMetierConfig;

/**
 * @author Fenonantenaina
 *
 */
@ContextConfiguration(classes=AcceptanceTestServiceMetierConfig.class)
public class PersonneSMTest extends SpringSerenityUnitTest {
    
	@Autowired
	private PersonneSM personneSM;
	
	private int idPersonne = 0;
	
	@Before
	public void setUpTest(){
		idPersonne = 1;
	}
	
	@Test
	public void recupererUnePersonneParSonId(){
		Personne personne = this.personneSM.findById(idPersonne);
		assertThat(personne, notNullValue());
		assertThat(personne, hasProperty("id", equalTo(idPersonne)));
	}

}
