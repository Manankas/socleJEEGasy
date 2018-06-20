#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.service.applicatif;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import ${package}.common.mapper.ToDtoConverter;
import ${package}.common.service.metier.GenericSM;

/**
 * @author Fenonantenaina
 *
 * @param <TEntity>
 * @param <TRepository>
 */
@Transactional
public abstract class GenericSAImpl<TEntity, TDto, TId extends Serializable, TServiceSM extends GenericSM<TEntity,TId>, TDtoConverter extends ToDtoConverter<TDto,TEntity>> implements GenericSA<TDto, TId>  {

	protected static final Logger logger = LoggerFactory.getLogger(GenericSAImpl.class);
	
	@Autowired
	protected TServiceSM serviceSM;
	
	@Autowired
	protected TDtoConverter converter;
	
	@Override
	public TDto findById(TId id) {
		TDto result = null;
		try{
			TEntity entite = this.serviceSM.findById(id);
			result = this.converter.convertToDto(entite);
		}
		catch(Exception e){
			logger.error("Erreur de récupération d'un rôle par son id : " + id,e);
		}
		return result;
	}

	@Override
	public Collection<TDto> findAll() {
		Collection<TDto> results = null;
		try{
			List<TEntity> entites = this.serviceSM.findAll();
			results = this.converter.convertToDto(entites);
		}
		catch(Exception e){
			logger.error("Erreur de récupération de la liste.",e);
		}
		return results;
	}

	@Override
	public Collection<TDto> findAll(int page, int rowPerPage) {
		Collection<TDto> results = null;
		try{
			Page<TEntity> entites = this.serviceSM.findAll(page, rowPerPage);
			results = this.converter.convertToDto(entites.getContent());
		}
		catch(Exception e){
			logger.error("Erreur de récupération de la liste.",e);
		}
		return results;
	}

	@Override
	public long count() {
		return this.serviceSM.count();
	}
	
}
