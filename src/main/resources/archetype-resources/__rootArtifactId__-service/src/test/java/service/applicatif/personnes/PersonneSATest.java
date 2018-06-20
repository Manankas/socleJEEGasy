#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.applicatif.personnes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsNull.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import ${package}.common.test.junit.SpringSerenityUnitTest;
import ${package}.data.dto.json.personnes.PersonneDto;
import ${package}.service._config.applicatif.AcceptanceTestServiceApplicatifConfig;

/**
 * @author Fenonantenaina
 *
 */
@ContextConfiguration(classes=AcceptanceTestServiceApplicatifConfig.class)
public class PersonneSATest extends SpringSerenityUnitTest {
    
	@Autowired
	private PersonneSA personneSA;
	
	private int idPersonne = 0;
	
	@Before
	public void setUpTest(){
		idPersonne = 1;
	}
	
	@Test
	public void recupererUnePersonneParSonId(){
		PersonneDto personne = this.personneSA.findById(idPersonne);
		assertThat(personne, notNullValue());
		assertThat(personne, hasProperty("id", equalTo(idPersonne)));
	}

}
