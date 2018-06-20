#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constraint.factory.filariane;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import ${package}.common.web.filariane.FilAriane;

/**
 * @author Fenonantenaina
 *
 */
public class FilArianeFactoryImpl implements FilArianeFactory {

	private MessageSource messageSource;
		
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public FilAriane homeFilAriane() {
		FilAriane filariane = new FilAriane("HOME", messageSource.getMessage("menu.home.text", null, LocaleContextHolder.getLocale()), "/", null);
		filariane.setDescription("Ceci est un description pour Acceuil");
		return filariane;
	}

	@Override
	public FilAriane dashboardFilAriane() {
		FilAriane filariane = this.homeFilAriane();
		FilAriane dashboard = new FilAriane("DASHBOARD", messageSource.getMessage("menu.dashboard.text", null, LocaleContextHolder.getLocale()), "/dashboard", null);
		filariane.setNext(dashboard);
		dashboard.setDescription("Ceci est un description pour Dashboard");
		return filariane;
	}

}
