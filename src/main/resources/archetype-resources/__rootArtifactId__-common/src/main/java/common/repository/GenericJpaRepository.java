#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * @author Fenonantenaina
 *
 * @param <TEntity>
 * @param <TId>
 */
@NoRepositoryBean
public interface GenericJpaRepository<TEntity, TId extends Serializable> extends JpaRepository<TEntity, TId>, JpaSpecificationExecutor<TEntity> {

}
