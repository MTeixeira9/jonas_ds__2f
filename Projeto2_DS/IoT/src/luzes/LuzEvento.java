package luzes;

import com.bezirk.middleware.messages.Event;

public class LuzEvento extends Event{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cor;
	
	public LuzEvento(String cor) {
		this.cor = cor;
	}
	
	public String getCor() {
		return cor;
	}	
	
	public String toString() {
		return "Luz de cor: " + cor;
	}

}
