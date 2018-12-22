package apectJ;

import java.io.IOException;
import ui.UIRecetorEventos;

import com.bezirk.middleware.messages.Event;

import atividade.AtividadeBD;
import atividade.AtividadeEvent;
import botao.BotaoEvento;
import contactos.AlertaContactos;
import contactos.ContactoBD;
import i18n.I18N;
import i18n.Messages;
import inatividade.InatividadeEvent;

public aspect Alertas {
	
	private AlertaContactos alertaC = new AlertaContactos();
	
	before(Event e) : execution(void UIRecetorEventos.receiveEvent(..)) && args(e,*) {
		
		if(e instanceof AtividadeEvent) {
			
			final AtividadeEvent atv = (AtividadeEvent) e;
			if(AtividadeBD.atividadeDetetada(atv.getDivisao(), atv.getHora())) {
				System.out.println(atv.toString());
			}
		
		}else if(e instanceof InatividadeEvent) {
			
			try {
				alertaC.mandarSMSparaContactos(new ContactoBD(), I18N.getString(Messages.MSG_ALERTA));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		}else if(e instanceof BotaoEvento) {
			try {
				alertaC.mandarSMSparaContactos(new ContactoBD(), I18N.getString(Messages.SMS));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}
