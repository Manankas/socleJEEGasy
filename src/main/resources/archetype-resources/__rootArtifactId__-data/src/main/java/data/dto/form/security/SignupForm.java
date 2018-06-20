#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.dto.form.security;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import ${package}.common.validator.FieldMatch;
import ${package}.data.entites.security.Utilisateur;

/**
 * @author Fenonantenaina
 *
 */
@FieldMatch.List({
	@FieldMatch(field="passwordVerify", value = "password.equals(passwordVerify)", applyIf = "password != null", message = SignupForm.PASSWORD_MESSAGE)
})
public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";
	public static final String PASSWORD_MESSAGE = "{password.not.match.message}";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String username;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;
    
    private String passwordVerify;
    
    @Email(message=SignupForm.EMAIL_MESSAGE)
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Utilisateur createAccount() {
        Utilisateur user = new Utilisateur();
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setEmail(getEmail());
        return user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordVerify() {
		return passwordVerify;
	}

	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}
	
}
