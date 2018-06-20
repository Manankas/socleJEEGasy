#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository.adresses;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.number.OrderingComparison.comparesEqualTo;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ${package}.common.test.junit.SpringSerenityUnitTest;
import ${package}.data.entites.Adresse;
import ${package}.repository._config.AcceptanceTestRepositoryConfig;

import net.thucydides.core.annotations.Title;

/**
 * @author Fenonantenaina
 *
 */
@DataJpaTest
@Transactional
@ContextConfiguration(classes=AcceptanceTestRepositoryConfig.class)
public class AdresseRepositoryTest extends SpringSerenityUnitTest {//SpringUnitTest{//SpringSerenityUnitTest {

	@Autowired
	private AdresseRepository adresseRepository;
	
	private long totalRowCount = 0;
	
	private int idAdresse = 0;
	
	@Before
	public void setUpTest(){
		Optional<Adresse> optAdrs = this.adresseRepository.findById(idAdresse);
		if(!optAdrs.isPresent()){
			Adresse adresse = new Adresse();
			adresse.setRue("Rue Randriamampandry");
			adresse.setVille("Antananarivo");
			
			this.adresseRepository.save(adresse);
			idAdresse = adresse.getId();
		}
		
		totalRowCount = this.adresseRepository.count();
	}
	
	@Title("Je voudrais créer une adresse")
	@Test
	public void creerUneAdresse(){
		Adresse adrs = new Adresse();
		adrs.setRue("Rue Randriamampandry");
		adrs.setVille("Antananarivo");
		
		this.adresseRepository.save(adrs);
		
		List<Adresse> adresses = this.adresseRepository.findAll();
		assertThat(adresses, hasSize((int)totalRowCount + 1));
	}
	
	@Title("Je voudrais mettre à jour une adresse")
	@Test
	public void mettreAJourUneAdresse(){
		final String ville = "Analakely";
		final String rue = "Rue Black";
		Adresse adrs = this.adresseRepository.getOne(idAdresse);
		adrs.setRue(rue);
		adrs.setVille(ville);
    	this.adresseRepository.save(adrs);
    	Adresse adresse = this.adresseRepository.getOne(idAdresse);
    	assertThat(adresse, hasProperty("ville", equalTo(ville)));
    	assertThat(adresse, hasProperty("rue", equalTo(rue)));
	}
	
	@Title("Je voudrais supprimer une adresse de la base")
	@Test
	public void supprimerUneAdresse(){
		Adresse adrs = this.adresseRepository.getOne(idAdresse);
    	this.adresseRepository.delete(adrs);
    	Optional<Adresse> optAdrs = this.adresseRepository.findById(idAdresse);
    	assertThat(this.adresseRepository.count(),comparesEqualTo(totalRowCount - 1));
    	assertThat(optAdrs.isPresent(), equalTo(false));
	}
	
}
