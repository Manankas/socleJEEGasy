#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Pour verifier si deux champs ont la même valeur
 * @author Fenonantenaina
 *
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
	
	String message() default FieldMatchValidator.DEFAULT_MESSAGE;

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
	 * Perform validation only if this SpEL expression evaluates to <tt>true</tt>.
	 * @return
	 */
	String applyIf() default "";
	
    /**
     * Validation expression in SpEL.
     *
     */
    String value();
    
    /**
     * Le champ cible
     * @return
     */
    String field();

	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		FieldMatch[] value();
	}
}
