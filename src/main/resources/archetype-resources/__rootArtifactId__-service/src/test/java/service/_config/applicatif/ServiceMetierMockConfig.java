#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service._config.applicatif;

import java.util.Arrays;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import ${package}.data.entites.Adresse;
import ${package}.data.entites.Personne;
import ${package}.service.metier.adresses.AdresseSM;
import ${package}.service.metier.personnes.PersonneSM;
import ${package}.service.metier.security.UserSecuritySM;

/**
 * @author Fenonantenaina
 *
 */
@TestConfiguration
public class ServiceMetierMockConfig {

	@Bean
	public PersonneSM personneSM(){
		//Adresse
		Adresse adrs = new Adresse();
		adrs.setId(1);
		adrs.setRue("Rue Randriamampandry");
		adrs.setVille("Antananarivo");
		List<Personne> personnes = Arrays.asList(
				new Personne() {{
					setId(1);
					setNom("Rakoto");
					setPrenom("Balita");
					setAdresseId(adrs.getId());
					setAdresse(adrs);
				}}
		);
		
		PersonneSM spy = Mockito.mock(PersonneSM.class);
		Mockito.when(spy.count()).thenReturn(1l);
		Mockito.when(spy.findAll()).thenReturn(personnes);
		Mockito.when(spy.findById(1)).thenReturn(personnes.get(0));
		
		return spy;
	}
	
	@Bean
	public AdresseSM adresseSM(){
		return Mockito.mock(AdresseSM.class);
	}
	
	@Bean
	public UserSecuritySM userSecuritySM(){
		return Mockito.mock(UserSecuritySM.class);
	}
	
}
