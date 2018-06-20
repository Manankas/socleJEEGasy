#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.controller.security.signup;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ${package}.common.web.MessageHelper;
import ${package}.data.dto.form.security.SignupForm;
import ${package}.data.entites.security.Utilisateur;
import ${package}.service.metier.security.UserSecuritySM;
import ${package}.service.metier.security.UserSecuritySMImpl;

/**
 * @author Fenonantenaina
 *
 */
@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

//	@Autowired
	private UserSecuritySM userSecuritySM;
	
	@RequestMapping(value = "signup")
	public String signup(Model model) {
		model.addAttribute(new SignupForm());
        return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		//Account account = accountRepository.save(signupForm.createAccount());
		Utilisateur user = signupForm.createAccount();
		userSecuritySM.signin(user);
        MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}
}
