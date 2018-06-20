#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.rules;

/**
 * Règle de décision. On peut avoir le status de l'objet selon la décision.
 * @author Fenonantenaina
 *
 * @param <TObject>
 * @param <TEnum>
 */
public abstract class RuleDecision<TObject extends Object, TEnum extends Enum<?>> extends AbstractComponent<TObject> {

	   private TEnum status;
	   
	   public TEnum getStatus(){
		   return status;
	   }
	   
	   protected RuleDecision(){}
	   
	   protected RuleDecision(TEnum defaultValue){status = defaultValue;}
	   
	   public void execute(TObject object) throws Exception {
		   status = makeDecision(object);
	   }

	   protected abstract TEnum makeDecision(TObject object) throws Exception;
}
