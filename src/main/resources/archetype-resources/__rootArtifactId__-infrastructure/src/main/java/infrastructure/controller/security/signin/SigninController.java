#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.controller.security.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fenonantenaina
 *
 */
@Controller
public class SigninController {

	@RequestMapping(value = "signin")
	public String signin() {
        return "signin/signin";
    }
}
