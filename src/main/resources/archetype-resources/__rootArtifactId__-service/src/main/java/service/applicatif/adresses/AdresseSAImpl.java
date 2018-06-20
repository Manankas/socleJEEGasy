#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.service.applicatif.adresses;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${package}.common.service.applicatif.GenericSAImpl;
import ${package}.common.service.operations.OperationFlags;
import ${package}.constraint.converter.adresses.AdresseConverter;
import ${package}.data.dto.json.adresses.AdresseDto;
import ${package}.data.entites.Adresse;
import ${package}.service.metier.adresses.AdresseSM;

/**
 * @author Fenonantenaina
 *
 */
@Service("adresseSA")
public class AdresseSAImpl extends GenericSAImpl<Adresse, AdresseDto, Integer, AdresseSM, AdresseConverter> implements AdresseSA {

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public OperationFlags update(AdresseDto dto) {
		OperationFlags result = OperationFlags.NO_OPERATION;
		try{
			Adresse adresse = this.serviceSM.findById(dto.getId());
			Adresse tmpAdresse = this.converter.convertFromDto(dto);
			adresse = this.converter.merge(tmpAdresse, adresse);
			this.serviceSM.update(adresse);
		}
		catch(Exception e){
			result = OperationFlags.OPERATION_FAILED;
			logger.error("Erreur pendant la MAJ de l'adresse : " + dto.getRue(),e);
		}
		return result;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public OperationFlags insert(AdresseDto dto) {
		OperationFlags result = OperationFlags.NO_OPERATION;
		if(dto.getId() == null){
			try{
			
				Adresse adresse = this.converter.convertFromDto(dto);
				this.serviceSM.insert(adresse);
				result = OperationFlags.OPERATION_SUCCESS;
			}
			catch(Exception e){
				result = OperationFlags.OPERATION_FAILED;
				logger.error("Erreur pendant la création de l'adresse : " + dto.getRue(),e);
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
				logger.error("Erreur pendant la suppression de l'adresse : " + id,e);
			}
		}
		return result;
	}

}
