#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Fenonantenaina
 *
 */
@Component
@ConfigurationProperties(prefix="application.i18n.dir")
public class LocalisationProperty {

	private String page;

    private String  control;
    
    private String message;
    
    private String httpError;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHttpError() {
		return httpError;
	}

	public void setHttpError(String httpError) {
		this.httpError = httpError;
	}
    
	
}
