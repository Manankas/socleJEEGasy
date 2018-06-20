#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.controller.home;

import java.security.Principal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ${package}.data.dto.form.formatter.FormatterDto;

/**
 * @author Fenonantenaina
 *
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, Principal principal) {
		model.addAttribute("messageController", "Message depuis le controller!");
		FormatterDto formatterDto = new FormatterDto();
		Calendar cal = Calendar.getInstance();
		formatterDto.setDateCreation(cal.getTime());
		cal.add(Calendar.MONTH, 3);
		formatterDto.setDateLimite(cal.getTime());
		model.addAttribute(formatterDto);
		
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
	
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public ModelAndView index2(Principal principal) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("messageController", "Message depuis le controller!");
		FormatterDto formatterDto = new FormatterDto();
		Calendar cal = Calendar.getInstance();
		formatterDto.setDateCreation(cal.getTime());
		cal.add(Calendar.MONTH, 3);
		formatterDto.setDateLimite(cal.getTime()); 
		model.put("formatterDto",formatterDto);
		return principal != null ? new ModelAndView("home/homeSignedIn", model) :new ModelAndView("home/homeNotSignedIn", model);
	}
}
