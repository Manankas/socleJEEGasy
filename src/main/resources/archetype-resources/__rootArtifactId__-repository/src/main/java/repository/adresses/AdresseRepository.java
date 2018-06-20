#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository.adresses;

import ${package}.common.repository.GenericJpaRepository;
import ${package}.data.entites.Adresse;

/**
 * @author Fenonantenaina
 *
 */
public interface AdresseRepository extends GenericJpaRepository<Adresse,Integer> {

}
