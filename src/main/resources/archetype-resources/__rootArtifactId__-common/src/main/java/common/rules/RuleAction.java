#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.rules;

/**
 * Règle qui execute une autre action suite à une autre.
 * <p>On peut fournir l'action à executer selon une condition</p>
 * @author Fenonantenaina
 *
 * @param <TObject>
 */
public abstract class RuleAction<TObject extends Object> extends AbstractComponent<TObject>{

	   private AbstractComponent<TObject> nextAction = null;
	   
	   public void setNextAction(AbstractComponent<TObject> nextAction){
		   this.nextAction = nextAction;
	   }
	   
	   protected RuleAction(){}
	   
	   protected RuleAction(AbstractComponent<TObject> nextAction){this.nextAction = nextAction;}
	   
	   @Override
	   public void execute(TObject object) throws Exception {
	      this.doExecute(object);
	      if(nextAction != null)
	    	  nextAction.execute(object);
	   }
	   
	   protected abstract void doExecute(TObject object) throws Exception;
	   
}
