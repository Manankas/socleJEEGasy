#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service._config.metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import ${package}.data.entites.Adresse;
import ${package}.data.entites.Personne;
import ${package}.repository.adresses.AdresseRepository;
import ${package}.repository.personnes.PersonneRepository;
import ${package}.repository.security.authority.AuthorityRepository;
import ${package}.repository.security.user.UtilisateurRepository;

/**
 * @author Fenonantenaina
 *
 */
@TestConfiguration
public class RepositoryMockConfig {

    @Bean
    public PlatformTransactionManager transactionManager(){
    	PlatformTransactionManager spy = Mockito.mock(PlatformTransactionManager.class);
    	return spy;
    }
	
	@Bean
	public PersonneRepository personneRepository(){
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
					setAdresseId(1);
					setAdresse(adrs);
				}}
		);
		
		PersonneRepository spy = Mockito.mock(PersonneRepository.class);
		Mockito.when(spy.count()).thenReturn(1l);
		Mockito.when(spy.findAll()).thenReturn(personnes);
		Mockito.when(spy.getOne(1)).thenReturn(personnes.get(0));
		
		return spy;
	}
	
	@Bean
	public AdresseRepository adresseRepository(){
		List<Adresse> adresses = new ArrayList<Adresse>();
		Adresse adrs = new Adresse();
		adrs.setId(1);
		adrs.setRue("Rue Randriamampandry");
		adrs.setVille("Antananarivo");
		adresses.add(adrs);
		AdresseRepository spy = Mockito.mock(AdresseRepository.class);
		Mockito.when(spy.count()).thenReturn(1l);
		Mockito.when(spy.findAll()).thenReturn(adresses);
		Mockito.when(spy.getOne(1)).thenReturn(adrs);
		
		return spy;
	}
	
	@Bean
	public UtilisateurRepository utilisateurRepository(){
		UtilisateurRepository spy = Mockito.mock(UtilisateurRepository.class);
		return spy;
	}
	
	@Bean
	public AuthorityRepository authorityRepository(){
		AuthorityRepository spy = Mockito.mock(AuthorityRepository.class);
		return spy;
	}
	
}
