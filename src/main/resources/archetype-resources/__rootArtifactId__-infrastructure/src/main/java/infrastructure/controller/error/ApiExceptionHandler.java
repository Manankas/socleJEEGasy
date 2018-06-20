#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.google.common.base.Throwables;
import ${package}.common.web.error.ApiError;

/**
 * General error handler for the application.
 */
@ControllerAdvice(annotations=RestController.class)
class ApiExceptionHandler {

	/**
	 * Handle exceptions thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)	
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
	public ApiError exception(Exception exception, WebRequest request) {
		return new ApiError("500", Throwables.getRootCause(exception).getMessage());
	}
}