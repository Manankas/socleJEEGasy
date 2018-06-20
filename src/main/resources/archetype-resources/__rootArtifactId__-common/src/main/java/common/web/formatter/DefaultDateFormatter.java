#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.web.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

/**
 * @author Fenonantenaina
 *
 */
public class DefaultDateFormatter implements Formatter<Date> {
	
    private MessageSource messageSource;
    
    public DefaultDateFormatter(MessageSource messageSource){
    	this.messageSource = messageSource;
    }
	
	@Override
	public String print(final Date object,final Locale locale) {
        final SimpleDateFormat dateFormat = createDateFormat(locale);
        return object != null ? dateFormat.format(object) : null;
	}

	@Override
	public Date parse(final String text,final Locale locale) throws ParseException {
        final SimpleDateFormat dateFormat = createDateFormat(locale);
        return text != null ? dateFormat.parse(text) : null;
	}
	
	private SimpleDateFormat createDateFormat(final Locale locale){
        final String format = this.messageSource.getMessage("date.format", null, locale);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        return dateFormat;
	}

}
