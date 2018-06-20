#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.infrastructure._database;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ${package}.common.databases.AbstractInitialDataLoader;
import ${package}.data.entites.Adresse;
import ${package}.data.entites.Personne;
import ${package}.data.entites.security.Authority;
import ${package}.data.entites.security.AuthorityId;
import ${package}.data.entites.security.Utilisateur;
import ${package}.repository.adresses.AdresseRepository;
import ${package}.repository.personnes.PersonneRepository;
import ${package}.repository.security.authority.AuthorityRepository;
import ${package}.repository.security.user.UtilisateurRepository;

/**
 * @author Fenonantenaina
 *
 */
@Component
public class InitialDataLoader extends AbstractInitialDataLoader {

	private static final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PersonneRepository personneRepository;
	
	@Autowired
	private AdresseRepository adresseRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${symbol_dollar}{application.database.generate.data}")
	private boolean generateData;
	
	@Value("${symbol_dollar}{application.database.file.user}")
	private Resource fileDefaultUser;
	
	@Transactional
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!this.generateData) return;
		
		long userCount = this.utilisateurRepository.count();
		boolean isDataExists = userCount > 0;  
		
		if(isDataExists){
	        this.generateData = false;
			return;
		}
		
		logger.info("Génération de la base de données");
		//CREATE USER
		insertRoleAndUserFromFile();
        
		//generation données test
		insertTestData();
		
        this.generateData = false;
        
        logger.info("Fin génération de la base de données");
		
	}
	
	
	@Transactional
	private void insertRoleAndUserFromFile(){
		//USER ADMIN
		try(BufferedReader br = new BufferedReader(new FileReader(this.fileDefaultUser.getFile())) ) {
		    String line = br.readLine();
		    String[] values = null;
		    Utilisateur user = null;
		    Authority auth = null;
		    do{
		    	if(!StringUtils.isEmpty(line) && !StringUtils.startsWith(line, "--")) {
		    		values = StringUtils.split(line, '|');
		    		if(values.length>=4){
		    			//USER ADMIN
		    	        user = new Utilisateur();
		    	        user.setUsername(values[0].trim());
		    	        user.setPassword(passwordEncoder.encode(values[1].trim()));
		    	        user.setEmail(values[2].trim());
		    	        values = StringUtils.split(values[3], ',');
		    	        if(values != null){
			    	        logger.info("=> Insertion utilisateur : " + user.getUsername());
			    	        utilisateurRepository.save(user);
			    	        for (String role : values) {
								auth = new Authority();
								auth.setId(new AuthorityId());
								auth.getId().setAuthority(role);
								auth.getId().setUsername(user.getUsername());
								logger.info("=> Insertion rôle : " + auth.getAuthority());
								this.authorityRepository.save(auth);
							}

		    	        }
		    		}
		    	}
		    	line = br.readLine();
		    }
		    while (!StringUtils.isEmpty(line));
		}
		catch(Exception e){
			logger.error("Erreur pendant la génération de la base de données ",e);
			e.printStackTrace();
		}
	}
	
	@Transactional
	private void insertTestData(){
		Personne pers = null;
		Adresse adrs = null;
		for(int i=1; i<6; i++){
			adrs = new Adresse();
			adrs.setRue("Lalana " + i * 25);
			adrs.setVille("Antananarivo " + i);
			this.adresseRepository.save(adrs);
			for(int j=1; j<3; j++){
				pers = new Personne();
				pers.setNom("Anarana 00" + (i*10 + j*30));
				pers.setPrenom("Prénom 00" + (i*10 + j*30));
				pers.setNom2("Anarana 00" + (i*10 + j*30));
				pers.setAdresseId(adrs.getId());
				this.personneRepository.save(pers);
			}
		}
	}
	
}
