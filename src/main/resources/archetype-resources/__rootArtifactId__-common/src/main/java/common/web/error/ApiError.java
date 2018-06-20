#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.web.error;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Fenonantenaina
 *
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiError {

	private String statusCode;
	
	private String message;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ApiError(String code, String message){
		this.statusCode = code;
		this.message = message;
	}
}
