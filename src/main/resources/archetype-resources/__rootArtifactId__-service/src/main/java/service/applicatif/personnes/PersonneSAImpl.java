#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.service.applicatif.personnes;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${package}.common.service.applicatif.GenericSAImpl;
import ${package}.common.service.operations.OperationFlags;
import ${package}.constraint.converter.personnes.PersonneConverter;
import ${package}.data.dto.json.personnes.PersonneDto;
import ${package}.data.entites.Personne;
import ${package}.service.metier.personnes.PersonneSM;

/**
 * @author Fenonantenaina
 *
 */
@Service("personneSA")
public class PersonneSAImpl extends GenericSAImpl<Personne, PersonneDto,Integer, PersonneSM, PersonneConverter> implements PersonneSA {

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public OperationFlags update(PersonneDto dto) {
		OperationFlags result = OperationFlags.NO_OPERATION;
		try{
			Personne personne = this.serviceSM.findById(dto.getId());
			Personne tmpPersonne = this.converter.convertFromDto(dto);
			personne = this.converter.merge(tmpPersonne, personne);
			this.serviceSM.update(personne);
		}
		catch(Exception e){
			result = OperationFlags.OPERATION_FAILED;
			logger.error("Erreur pendant la MAJ de : " + dto.getNom(),e);
		}
		return result;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public OperationFlags insert(PersonneDto dto) {
		OperationFlags result = OperationFlags.NO_OPERATION;
		if(dto.getId() == null){
			try{
			
				Personne personne = this.converter.convertFromDto(dto);
				this.serviceSM.insert(personne);
				dto.setId(personne.getId());
				result = OperationFlags.OPERATION_SUCCESS;
			}
			catch(Exception e){
				result = OperationFlags.OPERATION_FAILED;
				logger.error("Erreur pendant la création de : " + dto.getNom(),e);
			}
		}
		return result;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public OperationFlags delete(Integer id) {
		OperationFlags result = OperationFlags.NO_OPERATION;
		if(id != null){
			try{
				this.serviceSM.delete(id);
				result = OperationFlags.OPERATION_SUCCESS;
			}
			catch(Exception e){
				result = OperationFlags.OPERATION_FAILED;
				logger.error("Erreur pendant la suppression de la personne : " + id,e);
			}
		}
		return result;
	}

	@Override
	public Collection<PersonneDto> findAllByName(String name) {
		return this.converter.convertToDto(this.serviceSM.findAllByName(name));
	}

}
