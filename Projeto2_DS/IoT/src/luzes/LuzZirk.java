package luzes;

import static i18n.Messages.DEVICE_RUNNING;

import java.util.Random;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

public class LuzZirk {
	
	private final Bezirk bezirk;
	private final EventSet luz = new EventSet(LuzEvento.class);
	
	public LuzZirk() {
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Luzes Zirk");
		luz.setEventReceiver(new EventSet.EventReceiver() {			
			@Override
			public void receiveEvent(Event e, ZirkEndPoint zep) {
				LuzEvento l = (LuzEvento) e;
				System.err.println("Luz " + l.getCor() + " ligada - Alerta recebido");
			}
		});
		bezirk.subscribe(luz);
	}


	public static void main(String[] args) {
		LuzZirk l = new LuzZirk();
		System.err.println("Luz Zirck ativado!");
	}

}
