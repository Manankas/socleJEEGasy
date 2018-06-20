#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.metier;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Fenonantenaina
 *
 * @param <TEntity>
 * @param <TRepository>
 */
@Transactional
public abstract class GenericSMImpl<TEntity,TId extends Serializable,TRepository extends JpaRepository<TEntity,TId>> implements GenericSM<TEntity, TId>  {

	@Autowired
	protected TRepository repository;
	
	@Override
	public TEntity findById(TId id) {
		return this.repository.getOne(id);
	}

	@Override
	public List<TEntity> findAll() {
		return this.repository.findAll();
	}
	
	@Override
	public Page<TEntity> findAll(int page, int rowPerPage) {
		return this.repository.findAll(PageRequest.of(page, rowPerPage));
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(TEntity entite) {
		this.repository.save(entite);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void insert(TEntity entite) {
		this.repository.save(entite);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void delete(TId id) {
		this.repository.deleteById(id);
	}
	
	@Override
	public long count(){
		return this.repository.count();
	}

}
