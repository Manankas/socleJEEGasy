#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.dto.form.formatter;

import java.util.Date;

/**
 * @author Fenonantenaina
 *
 */
public class FormatterDto {

	private Date dateCreation;
	
	private Date dateLimite;

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}
}
