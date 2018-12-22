package voz;

import com.bezirk.middleware.messages.Event;

public class VozEvento extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Voz voz;

	public VozEvento(String texto) {
		
		voz = new Voz();
		voz.falar(texto);
	}

}
