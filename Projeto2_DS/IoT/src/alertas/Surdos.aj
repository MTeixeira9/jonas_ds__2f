package alertas;

import com.bezirk.middleware.messages.Event;

import botao.BotaoEvento;
import contactos.AvisoContactos;
import contactos.ContactoBD;
import i18n.I18N;
import i18n.Messages;
import luzes.LuzEnviada;
import monitorAtividade.AtividadeEvent;
import monitorAtividade.InatividadeEvent;
import ui.UIRecetorEventos;

public aspect Surdos {

	before(Event e) : execution(void UIRecetorEventos.receiveEvent(..)) && args(e,*) {

		if(e instanceof AtividadeEvent) {
			LuzEnviada l = new LuzEnviada("verde");
			l.enviarLuz();
			System.out.println("LUZ VERDE ACESA - após atividade detetada");
		}else if(e instanceof InatividadeEvent) {
			LuzEnviada l = new LuzEnviada("vermelha");
			l.enviarLuz();
			System.out.println("LUZ VERMELHA ACESA - após período de inatividade");
		}else if(e instanceof BotaoEvento) {
			try {
				AvisoContactos alertaC = new AvisoContactos();
				alertaC.mandarSMSparaContactos(new ContactoBD(), I18N.getString(Messages.MSG_ALERTA_BOTAO));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
