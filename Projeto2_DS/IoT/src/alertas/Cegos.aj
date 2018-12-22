package alertas;

import com.bezirk.middleware.messages.Event;

import bd.ContactoBD;
import botao.BotaoEvento;
import contactos.AvisoContactos;
import i18n.I18N;
import i18n.Messages;
import luzes.LuzEnviada;
import monitorAtividade.AtividadeEvent;
import monitorAtividade.InatividadeEvent;
import ui.UIRecetorEventos;
import voz.VozEnviada;

public aspect Cegos {

	before(Event e) : execution(void UIRecetorEventos.receiveEvent(..)) && args(e,*) {

		if(e instanceof AtividadeEvent) {
			Thread t = new Thread() {
				@Override
				public void run() {
					super.run();
					try {
						VozEnviada voz = new VozEnviada("Alert! An activity was detected");
						voz.enviarVoz();
						System.out.println("Voz ativada - após atividade detetada"); 
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
			
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
