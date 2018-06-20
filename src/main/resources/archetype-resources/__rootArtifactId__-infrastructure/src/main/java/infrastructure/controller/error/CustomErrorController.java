#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.controller.error;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Throwables;

@Controller
class CustomErrorController {
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Display an error page, as defined in web.xml <code>custom-error</code> element.
	 */
	@RequestMapping("generalError")	
	public String generalError(HttpServletRequest request, HttpServletResponse response, Model model) {
		// retrieve some useful information from the request
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		// String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}

		Locale locale = LocaleContextHolder.getLocale();	
		String exceptionMessage = messageSource.getMessage("http.error." + statusCode,null,null,locale);
		if(StringUtils.isEmpty(exceptionMessage)){exceptionMessage = getExceptionMessage(throwable, statusCode);};
		
		Object[] vars = new Object[3];
		vars[0] = statusCode;
		vars[1] = requestUri;
		vars[2] = exceptionMessage;
		
		String message = messageSource.getMessage("http.error.message", vars, locale);
		model.addAttribute("errorMessage", message);
        return "error/general";
	}

	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			return Throwables.getRootCause(throwable).getMessage();
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();
	}

}
