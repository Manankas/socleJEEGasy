#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository.security.authority;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import ${package}.common.repository.GenericJpaRepository;
import ${package}.data.entites.security.Authority;
import ${package}.data.entites.security.AuthorityId;

/**
 * @author Fenonantenaina
 *
 */
public interface AuthorityRepository extends GenericJpaRepository<Authority, AuthorityId> {

	public Collection<Authority> findAllByUserUsername(String username);
	
	@Query("select auth from Authority auth where auth.id.username = ?${symbol_pound}{ principal?.id }")
	public Collection<Authority> findAllByCurrentUser();
	
}
