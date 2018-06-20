#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.api.personnes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${package}.common.service.operations.OperationFlags;
import ${package}.data.dto.json.personnes.PersonneDto;
import ${package}.service.applicatif.personnes.PersonneSA;

import io.swagger.annotations.ApiOperation;

/**
 * Personne Api Prototype
 * @author Fenonantenaina
 *
 */
@RestController
@RequestMapping("/api/personnes")
public class PersonneApiController {

	@Autowired
	private PersonneSA personneSA;
	
	@ApiOperation(value="Find a Personne by Id")
	@RequestMapping(path="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonneDto> get(@PathVariable(value="id") int id){
		final int persId = id;
		PersonneDto dto = personneSA.findById(persId);
		return new ResponseEntity<PersonneDto>(dto, HttpStatus.OK);
	}
	
	@ApiOperation("Find Personne list, filtered by name if name not empty")
	@RequestMapping(path={"","/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonneDto>> getAll(@RequestParam(name="name",required=false) String name){
		List<PersonneDto> personnes = StringUtils.isEmpty(name)?
				new ArrayList<PersonneDto>(this.personneSA.findAll()) :
				new ArrayList<PersonneDto>(	personneSA.findAllByName(name));
		return new ResponseEntity<List<PersonneDto>>(personnes, HttpStatus.OK);
	}
	
	@ApiOperation("Delete Personne by Id")
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonneDto> delete(@PathVariable(value="id") int id){
		OperationFlags flag = this.personneSA.delete(id);
		return new ResponseEntity<PersonneDto>(flag == OperationFlags.OPERATION_SUCCESS ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation("Update Personne")
	@RequestMapping(path="/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonneDto> update(@PathVariable(value="id") int id, @RequestBody PersonneDto personne){
		OperationFlags flag = this.personneSA.update(personne);		
		return new ResponseEntity<PersonneDto>(personne,flag == OperationFlags.OPERATION_SUCCESS ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation("Create new Personne")
	@RequestMapping(path={"","/"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonneDto> create(@RequestBody PersonneDto personne){
		OperationFlags flag = this.personneSA.insert(personne);		
		return new ResponseEntity<PersonneDto>(personne, flag == OperationFlags.OPERATION_SUCCESS ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
