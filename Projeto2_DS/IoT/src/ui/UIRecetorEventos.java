package ui;

import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet.EventReceiver;

import botao.BotaoEvento;
import monitorAtividade.AtividadeEvent;
import monitorAtividade.InatividadeEvent;

public class UIRecetorEventos implements EventReceiver{

	@Override
	public void receiveEvent(Event e, ZirkEndPoint z) {
		
		if(e instanceof AtividadeEvent) {
			System.out.println("Recebi um evento de Atividade!!!");
		}
		
		if(e instanceof InatividadeEvent) {
			System.out.println("Recebi um evento de Inatividade!!!");
		}
		
		if(e instanceof BotaoEvento) {
			System.out.println("Recebi um evento de BOTÃO!!!");
		}
		
		
	}
	
	

}
