#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Validateur pour {@link FieldMatch}
 * @author Fenonantenaina
 *
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	public static final String DEFAULT_MESSAGE = "Fields not match";
	
    private Expression expression=null;
    
    private Expression applyIfExpression=null;
    
    private String fieldTarget=null;
    
    private String message = null;
    
    
	@Override
	public void initialize(FieldMatch constraint) {
        ExpressionParser parser = new SpelExpressionParser();

        expression = parser.parseExpression(constraint.value());
        if (!StringUtils.isEmpty(constraint.applyIf())) {
            applyIfExpression = parser.parseExpression(constraint.applyIf());
        }
        
        message = constraint.message();
        fieldTarget = constraint.field();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        EvaluationContext evalContext = createEvaluationContext(value);

        boolean valid = false;
        if (isApplyIfValid(evalContext)) {
        	valid = evaluate(expression, evalContext);
        	if(!valid){
        		if(!StringUtils.equals(message, DEFAULT_MESSAGE)){
        			context.disableDefaultConstraintViolation();
        		}
        		ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(message);
        		if(!StringUtils.isEmpty(fieldTarget)){
        			builder.addPropertyNode(fieldTarget).addConstraintViolation();	
        		} 		
        	}
        }
        return valid;
	}
	
    private boolean isApplyIfValid(EvaluationContext context) {
        if (applyIfExpression == null) return true;
        return evaluate(applyIfExpression, context);
    }

    private boolean evaluate(Expression expression, EvaluationContext context) {
        Boolean result = expression.getValue(context, Boolean.class);
        return result == null ? false : result;
    }
    
    private StandardEvaluationContext createEvaluationContext(Object rootObject) {
        return new StandardEvaluationContext(rootObject);
    }

}
