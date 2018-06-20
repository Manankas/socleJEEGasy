#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.rules;

/**
 * Règle qui execute une action selon la décision.
 * <p>On a deux actions possible : [positiveRule] à executer si la décision est positive et [negativeRule] si la décision est négative</p>
 *  
 * @author Fenonantenaina
 * 
 * @param <TObject>
 */
public abstract class SimpleRule<TObject extends Object> extends AbstractComponent<TObject> {

	   private AbstractComponent<TObject> positiveRule;
	   private AbstractComponent<TObject> negativeRule;
	   
	   public SimpleRule(AbstractComponent<TObject> positiveRule, AbstractComponent<TObject> negativeRule){
		   if(positiveRule == null && negativeRule == null) throw new RuntimeException("Les deux actions possible ne peuvent pas être null en même temps");
		   this.positiveRule = positiveRule;
		   this.negativeRule = negativeRule;
	   }
	   
	   @Override
	   public void execute(TObject object) throws Exception {
	      boolean outcome = makeDecision(object);
	      if(outcome){
	    	 if(positiveRule != null) positiveRule.execute(object);
	      }
	      else{
	    	  if(negativeRule != null) negativeRule.execute(object);
	      }
	   }
	   
	   protected abstract boolean makeDecision(TObject object) throws Exception;
	   
}
