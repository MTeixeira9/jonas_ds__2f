package voz;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;


public class VozZirk {
	
	private final Bezirk bezirk;
	private final EventSet voz = new EventSet(VozEvento.class);
	
	public VozZirk() {
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Voz Zirk");
		voz.setEventReceiver(new EventSet.EventReceiver() {			
			@Override
			public void receiveEvent(Event e, ZirkEndPoint zep) {
				VozEvento l = (VozEvento) e;
				System.err.println("");
			}
		});
		bezirk.subscribe(voz);
	}


	public static void main(String[] args) {
		VozZirk l = new VozZirk();
		System.err.println("Voz Zirck ativado!");
	}
	

}
