package luzes;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class LuzEnviada {
	
	private Bezirk bezirk;
	String cor;
	
	public LuzEnviada(String cor) {
		this.cor = cor;
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Notificação de evento de Luz!");
		enviarLuz();
	}

	public void enviarLuz() {
		final LuzEvento luz = new LuzEvento(cor);
		bezirk.sendEvent(luz);
		
	}
	
	public static void main(String[] args) {
		LuzEnviada luz = new LuzEnviada("");
		System.out.println("Luzes ligadas - Alerta enviado");
		luz.enviarLuz();
	}	

}
