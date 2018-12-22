package voz;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import luzes.LuzEnviada;
import luzes.LuzEvento;

public class VozEnviada {

	private Bezirk bezirk;
	private String texto;
	
	public VozEnviada(String texto) {
		this.texto = texto;
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Notificação de evento de voz!");
		enviarVoz();
	}

	public void enviarVoz() {
		final VozEvento voz = new VozEvento(texto);
		bezirk.sendEvent(voz);
		
	}
	
	public static void main(String[] args) {
		VozEnviada voz = new VozEnviada("");
		System.out.println("Voz - Alerta enviado");
		voz.enviarVoz();
	}
	
}
