package botao;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class BotaoZirk {
	
	private static final String ID = "BotaoSensor";
	
	private Bezirk bezirk;
	
	public BotaoZirk() {
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk(ID);
	}
	
	public void pushBotao () {
		bezirk.sendEvent(new BotaoEvento());
		System.err.println("Botao Evento pressionado");
	}

	public static void main (String [] args) {
		BotaoZirk botaoSensor = new BotaoZirk();
		botaoSensor.pushBotao();
	}
}
