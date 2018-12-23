package botao;

import com.bezirk.middleware.messages.Event;

public class AvisoEvento extends Event{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Aviso aviso;
	
	public AvisoEvento(Aviso aviso) {
		this.aviso = aviso;
	}
	
	
	public String toString() {
		return aviso.toString();
	}

}
