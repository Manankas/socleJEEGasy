#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.web.filariane;

import java.util.Iterator;

/**
 * @author Fenonantenaina
 *
 */
public class FilAriane implements Iterable<FilAriane> {

	private String titre;
	
	private String description;
	
	private String codeMenu;
	
	private String url;
	
	private FilAriane next;
	
	public FilAriane(){
		this(null);
	}
	
	public FilAriane(FilAriane suivant){
		this(null, null, null, suivant);
	}
	
	public FilAriane(String code, String titre, String url, FilAriane suivant){
		this.codeMenu = code;
		this.url = url;
		this.titre = titre;
		this.next = suivant;
	}

	
	/**
	 * Retourne le titre de l'instance du filariane
	 * @return
	 */
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Retourne le code menu de l'instance du filariane
	 * @return
	 */
	public String getCodeMenu() {
		return codeMenu;
	}

	public void setCodeMenu(String codeMenu) {
		this.codeMenu = codeMenu;
	}

	public FilAriane getNext() {
		return next;
	}

	public void setNext(FilAriane next) {
		this.next = next;
	}
	
	/**
	 * Retourne l'url de l'instance du filariane
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Retourne la description de l'instance du filariane
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retourne le titre de la page courante
	 * @return
	 */
	public String getCurrentTitre(){
		if(this.next == null) return this.getTitre();
		else return this.next.getCurrentTitre();
	}
	
	/**
	 * Retourne la description de la page courante
	 * @return
	 */
	public String getCurrentDescription(){
		if(this.next == null) return this.getDescription();
		else return this.next.getCurrentDescription();
	}
	
	/**
	 * Retourne le code menu de la page courante
	 * @return
	 */
	public String getCurrentCodeMenu(){
		if(this.next == null) return this.getCodeMenu();
		else return this.next.getCurrentCodeMenu();
	}

	@Override
	public Iterator<FilAriane> iterator() {
		return new FilArianeIterator(this);
	}
	
	// Inner class example
	private static final class FilArianeIterator implements Iterator<FilAriane> {
		
	    private FilAriane current = null;

	    public FilArianeIterator(final FilAriane start) {
	        this.current = start;
	    }

	    public boolean hasNext() {
	        return this.current != null;
	    }

	    public FilAriane next() {
	        FilAriane next = this.current;
	        this.current = this.current.next;
	        return next;
	    }

	    public void remove() {
	        throw new UnsupportedOperationException();
	    }
	}
}
