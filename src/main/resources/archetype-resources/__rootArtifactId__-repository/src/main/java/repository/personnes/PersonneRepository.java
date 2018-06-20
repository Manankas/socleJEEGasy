#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository.personnes;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import ${package}.common.repository.GenericJpaRepository;
import ${package}.data.entites.Personne;

/**
 * @author Fenonantenaina
 *
 */
public interface PersonneRepository extends GenericJpaRepository<Personne,Integer> {

	@Query("select e from ${symbol_pound}{${symbol_pound}entityName} e where e.nom like ?1 or e.nom2 like ?1")
	public Collection<Personne> findByName(String nom);
	
}
