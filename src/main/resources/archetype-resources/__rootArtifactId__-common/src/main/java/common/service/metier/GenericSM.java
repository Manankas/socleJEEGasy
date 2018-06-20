#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.metier;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;


/**
 * @author Fenonantenaina
 *
 */
public interface GenericSM<TEntity, TId extends Serializable> {
	
	public TEntity findById(TId id);
	
	public List<TEntity> findAll();
	
	public Page<TEntity> findAll(int page, int rowPerPage);
	
	public void update(TEntity entite);
	
	public void insert(TEntity entite);
	
	public void delete(TId id);
	
	public long count();
	
}
