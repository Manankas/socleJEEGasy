#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

//import com.crypteron.Secure;
import ${package}.common.databases.typedefs.TypeDefEncryptedString;
import ${package}.common.databases.typedefs.TypeDefSaltedEncryptedString;

@Entity
@Table(name = "personnes")
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Type(type=TypeDefEncryptedString.TYPE_ENCRYPTED_STRING)
	private String nom;
	
	private String prenom;

	@Type(type=TypeDefSaltedEncryptedString.TYPE_ENCRYPTED_STRING)
	private String nom2;
	
	@Column(name="adresse_id")
	private int adresseId;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="adresse_id",updatable=false, insertable=false)
	private Adresse adresse;

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

	public int getAdresseId() {
		return adresseId;
	}

	public void setAdresseId(int adresseId) {
		this.adresseId = adresseId;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getNom2() {
		return nom2;
	}

	public void setNom2(String nom2) {
		this.nom2 = nom2;
	}
	
	
}
