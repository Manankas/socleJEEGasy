#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.metier.adresses;

import org.springframework.stereotype.Service;

import ${package}.common.service.metier.GenericSMImpl;
import ${package}.data.entites.Adresse;
import ${package}.repository.adresses.AdresseRepository;

/**
 * @author Fenonantenaina
 *
 */
@Service("adresseSM")
public class AdresseSMImpl extends GenericSMImpl<Adresse,Integer, AdresseRepository> implements AdresseSM {

}
