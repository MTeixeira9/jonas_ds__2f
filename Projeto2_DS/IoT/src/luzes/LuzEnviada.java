package luzes;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class LuzEnviada {
	
	private Bezirk bezirk;
	
	public LuzEnviada() {
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Notificação de evento de Luz!");
		enviarLuz();
	}

	public void enviarLuz() {
		final LuzEvento luz = new LuzEvento();
		bezirk.sendEvent(luz);
		
	}
	
	public static void main(String[] args) {
		LuzEnviada luz = new LuzEnviada();
		System.out.println("Luzes ligadas");
		luz.enviarLuz();
	}
	
	

}
