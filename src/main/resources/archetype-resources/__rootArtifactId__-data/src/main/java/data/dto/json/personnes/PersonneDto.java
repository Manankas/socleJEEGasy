#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.dto.json.personnes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ${package}.data.dto.json.adresses.AdresseDto;

/**
 * @author Fenonantenaina
 *
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown=true)
public class PersonneDto {

	private Integer id;
	
	private String nom;
	
	private String prenom;
	
	private AdresseDto adresse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public AdresseDto getAdresse() {
		return adresse;
	}

	public void setAdresse(AdresseDto adresse) {
		this.adresse = adresse;
	}
}
