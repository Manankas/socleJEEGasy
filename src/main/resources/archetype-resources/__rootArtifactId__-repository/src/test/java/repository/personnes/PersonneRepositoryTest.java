#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository.personnes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.hamcrest.number.OrderingComparison.comparesEqualTo;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ${package}.common.test.junit.SpringSerenityUnitTest;
import ${package}.data.entites.Adresse;
import ${package}.data.entites.Personne;
import ${package}.repository._config.AcceptanceTestRepositoryConfig;
import ${package}.repository.adresses.AdresseRepository;


/**
 * @author Fenonantenaina
 *
 */
@DataJpaTest
@Transactional
@ContextConfiguration(classes=AcceptanceTestRepositoryConfig.class)
public class PersonneRepositoryTest extends SpringSerenityUnitTest {//SpringUnitTest{//SpringSerenityUnitTest{

	@Autowired
	private AdresseRepository adresseRepository;
	
	@Autowired
	private PersonneRepository personneRepository;
	
	private Adresse adresse = null;
	
	private long totalRowCount = 0;
	
	private int idPersonne = 0;
	
	@PostConstruct
	public void initDatabase(){
		adresse = new Adresse();
		adresse.setRue("Rue Randriamampandry");
		adresse.setVille("Antananarivo");
		
		this.adresseRepository.save(adresse);
	}
	
	@Before
	public void setUpTest(){
		Optional<Personne> optPers = this.personneRepository.findById(idPersonne);
		if(!optPers.isPresent()){
	    	Personne personne = new Personne();
	    	personne.setNom("Rakoto");
	    	personne.setPrenom("Jean");
	    	personne.setAdresseId(adresse.getId());
	    	this.personneRepository.save(personne);
	    	idPersonne = personne.getId();
		}
    	totalRowCount = this.personneRepository.count();
	}
	
	@Test
	public void supprimerUnePersonne(){
		Optional<Personne> optPers = this.personneRepository.findById(idPersonne);
    	if(!optPers.isPresent()){
    		Assert.fail("No Personne entity found with id["+idPersonne+"]");
    	}
    	this.personneRepository.delete(optPers.get());
    	optPers = this.personneRepository.findById(idPersonne);
    	assertThat(this.personneRepository.count(),comparesEqualTo(totalRowCount - 1));
    	assertThat(optPers.isPresent(), equalTo(false));
	}
	
	@Test
	public void modifierUnePersonne(){
		final String nom = "RASOANOMENA";
    	Personne pers = this.personneRepository.getOne(idPersonne);
    	pers.setNom(nom);
    	this.personneRepository.save(pers);
    	Personne personne = this.personneRepository.getOne(idPersonne);
    	assertThat(pers, samePropertyValuesAs(personne));
	}
	
	@Test
	public void creerUnePersonne(){
    	Personne personne = new Personne();
    	personne.setNom("Mikolo");
    	personne.setPrenom("Barman");

    	personne.setAdresseId(adresse.getId());
    	this.personneRepository.save(personne);
    	
    	assertThat(this.personneRepository.count(),comparesEqualTo(totalRowCount + 1));
	}
}
