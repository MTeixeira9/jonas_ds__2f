package ui;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import bd.AtividadeBD;
import bd.AvisoBD;
import bd.ContactoBD;
import bd.InatividadeBD;
import botao.Aviso;
import botao.BotaoEvento;
import contactos.Contacto;
import i18n.I18N;
import i18n.Messages;
import monitorAtividade.Atividade;
import monitorAtividade.AtividadeEvent;
import monitorAtividade.InatividadeEvent;
import monitorAtividade.SensorAtividadeZirk;

public class UI {
	
	private Bezirk bezirk;
	
	public UI() {
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("UI");
	
		List<Class<? extends Event>> eventosSubsc = new ArrayList<>();
		eventosSubsc.add(BotaoEvento.class);
		eventosSubsc.add(AtividadeEvent.class);
		eventosSubsc.add(InatividadeEvent.class);
		
		final EventSet eventosSubscritos = new EventSet(converteArray(eventosSubsc));
		UIRecetorEventos recEv = new UIRecetorEventos();
		eventosSubscritos.setEventReceiver(recEv);
		bezirk.subscribe(eventosSubscritos);
		
	}

	private Class<? extends Event>[] converteArray(List<Class<? extends Event>> e) {
		@SuppressWarnings("unchecked")
		Class<? extends Event> [] res = new Class[e.size()];
		int i = 0;
		for(Class<? extends Event> classe: e) {
			res[i]=classe;
			i++;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		UI ui = new UI();
		start();
	}

	private static void start() throws IOException {

		//Iniciar BDs
		AtividadeBD ativBD = null;
		InatividadeBD inativBD = null;
		AvisoBD avisoBD = null;
		ContactoBD contactBD = null;
		
		try {
			ativBD = new AtividadeBD();
			inativBD = new InatividadeBD();
			avisoBD = new AvisoBD();
			contactBD = new ContactoBD();			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		ArrayList<Contacto> contactos = contactBD.getContactos();
		
		Scanner sc = new Scanner(System.in);
		boolean acabou = false;
		int operacao = 0;
		
		while (!acabou) {
			
			System.out.println(I18N.getString(Messages.SEPARADOR));
			System.out.println(I18N.getString(Messages.OPC_MENU));
			System.out.println(I18N.getString(Messages.OPC1));
			System.out.println(I18N.getString(Messages.OPC2));
			System.out.println(I18N.getString(Messages.OPC3));
			System.out.println(I18N.getString(Messages.OPC4));
			System.out.println(I18N.getString(Messages.OPC5));
			System.out.println(I18N.getString(Messages.OPC6));
			System.out.println(I18N.getString(Messages.SEPARADOR));
			operacao = sc.nextInt();
			
			switch(operacao) {
			
			//contacto
			case 1: {
				Scanner scC = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.CONTACTO_NOME));
				String nome = scC.nextLine();
				System.out.println(I18N.getString(Messages.CONTACTO_NUMERO));
				String numero = scC.nextLine();
				contactBD.insert(nome, numero);
				System.out.println(I18N.getString(Messages.SUCESSO_CONTACTO));
				break;
			}
			
			//criar aviso
			case 2: { 
				Scanner scA = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.AVISO_MSG));
				String msg = scA.nextLine();
				System.out.println(I18N.getString(Messages.DATA_IN));
				String dI = scA.nextLine();
				System.out.println(I18N.getString(Messages.DATA_F));
				String dF = scA.nextLine();
				System.out.println(I18N.getString(Messages.PERIOD));
				String period = scA.nextLine();
				avisoBD.insert(msg, dI, dF, period);
				System.out.println(I18N.getString(Messages.SUCESSO_AVISO_CRIAR));
				break;
			}
			
			//Apagar aviso
			case 3: {
				ArrayList<Aviso> avisos = avisoBD.getAvisos();
				Scanner scDel = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.MOSTRA_AVISOS));
				for (Aviso a: avisos) {
					System.out.println(a.toString());
				}
				int idApagar = Integer.parseInt(scDel.nextLine());
				avisoBD.delete(idApagar);
				System.out.println(I18N.getString(Messages.SUCESSO_AVISO_APAGAR));
				break;
			}
			
			//Simular evento de atividade
			case 4: {
				Scanner scAtiv = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.SIMULAR_ATIV));
				System.out.println(I18N.getString(Messages.PEDE_OK));
				String res = scAtiv.nextLine();
				
				break;
			}
			
			//Simular um evento de inatividade
			case 5: {
				Scanner scInativ = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.SIMULAR_INATIV));
				System.out.println(I18N.getString(Messages.PEDE_OK));
				String res = scInativ.nextLine();
				
				break;
			}
			
			//para terminar
			case 6: {
				acabou = true;
				break;
			}
			
			
			default: {
				break;
			}
				
			}
			
		}
		
		sc.close();
		System.exit(1);
		
	}

}
