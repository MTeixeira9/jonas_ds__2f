package atividade;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

public class AtividadeAssistantZirk {
	
	public AtividadeAssistantZirk() {
		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Atividade Assitant Zirk");
		System.err.println("Instância de Bezirk apanhada");
		
		final EventSet eventosAtividade = new EventSet(AtividadeEvent.class);
		
		eventosAtividade.setEventReceiver(new EventSet.EventReceiver() {
			
			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				if (event instanceof AtividadeEvent) {
					final AtividadeEvent ativEvent = (AtividadeEvent) event;
					System.out.println("\nEvento de atividade recebido: " + ativEvent.toString());
				}				
			}
		});
	}
	
	public static void main(String[] args) {
		new AtividadeAssistantZirk();
		System.err.println("Este produto tem um Assistente de Atividade");
	}

}
