package alertas;

import java.io.IOException;
import ui.UIRecetorEventos;

import com.bezirk.middleware.messages.Event;

import botao.BotaoEvento;
import contactos.AlertaContactos;
import contactos.ContactoBD;
import i18n.I18N;
import i18n.Messages;
import monitorAtividade.AtividadeBD;
import monitorAtividade.AtividadeEvent;
import monitorAtividade.InatividadeEvent;

public aspect Alertas {
	
	private AlertaContactos alertaC = new AlertaContactos();
	
	before(Event e) : execution(void UIRecetorEventos.receiveEvent(..)) && args(e,*) {
		
		if(e instanceof AtividadeEvent) {
			
			//final AtividadeEvent atv = (AtividadeEvent) e;
			try {
				alertaC.mandarSMSparaContactos(new ContactoBD(), I18N.getString(Messages.MSG_ALERTA_ATIV));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		}else if(e instanceof InatividadeEvent) {
			
			try {
				alertaC.mandarSMSparaContactos(new ContactoBD(), I18N.getString(Messages.MSG_ALERTA_INATIV));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		
		}else if(e instanceof BotaoEvento) {
			try {
				alertaC.mandarSMSparaContactos(new ContactoBD(), I18N.getString(Messages.MSG_ALERTA_BOTAO));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

}
