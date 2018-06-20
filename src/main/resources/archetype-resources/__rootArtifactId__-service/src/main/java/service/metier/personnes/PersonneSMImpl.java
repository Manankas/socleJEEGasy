#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.personnes;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ${package}.common.service.metier.GenericSMImpl;
import ${package}.data.entites.Personne;
import ${package}.repository.personnes.PersonneRepository;

/**
 * @author Fenonantenaina
 *
 */
@Service("personneSM")
public class PersonneSMImpl extends GenericSMImpl<Personne, Integer, PersonneRepository> implements PersonneSM {

	@Override
	public Collection<Personne> findAllByName(String nomPrenom) {
		return this.repository.findByName(nomPrenom);
	}

}
