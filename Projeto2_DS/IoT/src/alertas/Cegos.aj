package alertas;

import com.bezirk.middleware.messages.Event;

import botao.BotaoEvento;
import contactos.AvisoContactos;
import contactos.ContactoBD;
import i18n.I18N;
import i18n.Messages;
import monitorAtividade.AtividadeEvent;
import monitorAtividade.InatividadeEvent;
import ui.UIRecetorEventos;
import voz.VozEnviada;

public aspect Cegos {

	before(Event e) : execution(void UIRecetorEventos.receiveEvent(..)) && args(e,*) {

		if(e instanceof AtividadeEvent) {
			VozEnviada voz = new VozEnviada("Alert! An activity was detected");
			voz.enviarVoz();
			System.out.println("Voz ativada - após atividade detetada"); 

		}else if(e instanceof InatividadeEvent) {
			VozEnviada voz = new VozEnviada("Alert! It's been while snce i saw you moving");
			voz.enviarVoz();
			System.out.println("Voz ativada - após período de inatividade"); 
		}else if(e instanceof BotaoEvento) {
			try {
				AvisoContactos avisoC = new AvisoContactos();
				avisoC.mandarSMSparaContactos(new ContactoBD(), I18N.getString(Messages.MSG_ALERTA_BOTAO));

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
