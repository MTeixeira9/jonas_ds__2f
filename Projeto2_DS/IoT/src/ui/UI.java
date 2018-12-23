package ui;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import botao.Aviso;
import botao.AvisoBD;
import botao.AvisoTimer;
import botao.BotaoEvento;
import contactos.Contacto;
import contactos.ContactoBD;
import i18n.I18N;
import i18n.Messages;
import monitorAtividade.Atividade;
import monitorAtividade.AtividadeBD;
import monitorAtividade.AtividadeEvent;
import monitorAtividade.Inatividade;
import monitorAtividade.InatividadeBD;
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
		
		HashMap <Integer, AvisoTimer> avisosTimers = new HashMap<>();
		ArrayList<Aviso> avisos = avisoBD.getAvisos();
		for(Aviso a: avisos) {
			avisosTimers.put(a.getId(), new AvisoTimer(a));
		}
		
		
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
			System.out.println(I18N.getString(Messages.OPC7));
			System.out.println(I18N.getString(Messages.OPC8));
			System.out.println(I18N.getString(Messages.OPC9));
			System.out.println(I18N.getString(Messages.OPC10));
			System.out.println(I18N.getString(Messages.SEPARADOR));
			operacao = sc.nextInt();
			
			switch(operacao) {
			
			//criar contacto
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
			
			//eliminar contacto
			case 2: {
				ArrayList<Contacto> contactos = contactBD.getContactos();
				Scanner scDel = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.MOSTRA_CONTACTOS));
				for (Contacto a: contactos) {
					System.out.println(a.toString());
				}
				int idApagar = Integer.parseInt(scDel.nextLine());
				contactBD.delete(idApagar);
				System.out.println(I18N.getString(Messages.SUCESSO_CONTACTO_APAGAR));
				break;
			}
			
			//criar aviso
			case 3: { 
				Scanner scA = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.AVISO_MSG));
				String msg = scA.nextLine();
				System.out.println(I18N.getString(Messages.DATA_IN));
				String dI = scA.nextLine();
				System.out.println(I18N.getString(Messages.DATA_F));
				String dF = scA.nextLine();
				System.out.println(I18N.getString(Messages.PERIOD));
				String period = scA.nextLine();
				long p = Long.parseLong(period);
				int id = avisoBD.insert(msg, dI, dF, period);
				Aviso inserir = new Aviso(id, msg, dI, dF, p);
				avisosTimers.put(id, new AvisoTimer(inserir));
				System.out.println(I18N.getString(Messages.SUCESSO_AVISO_CRIAR));
				break;
			}
			
			
			//Apagar aviso
			case 4: {
				ArrayList<Aviso> avisosAp = avisoBD.getAvisos();
				Scanner scDel = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.MOSTRA_AVISOS));
				for (Aviso a: avisosAp) {
					System.out.println(a.toString());
				}
				int idApagar = Integer.parseInt(scDel.nextLine());
				avisoBD.delete(idApagar);
				avisosTimers.get(idApagar).stopTimer();
				System.out.println(I18N.getString(Messages.SUCESSO_AVISO_APAGAR));
				break;
			}
			
			//Simular evento de atividade
			case 5: {
				Scanner scAtiv = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.SIMULAR_ATIV));
				System.out.println(I18N.getString(Messages.PEDE_OK));
				String res = scAtiv.nextLine();
				
				break;
			}
			
			//Apagar atividade
			case 6: {
				ArrayList<Atividade> atividades = ativBD.getAtividades();
				Scanner scDel = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.MOSTRA_ATIVIDADES));
				for (Atividade a: atividades) {
					System.out.println(a.toStringUI());
				}
				int idApagar = Integer.parseInt(scDel.nextLine());
				ativBD.delete(idApagar);
				System.out.println(I18N.getString(Messages.SUCESSO_ATV_APAGAR));
				break;
			}
			
			
			//Simular um evento de inatividade
			case 7: {
				Scanner scInativ = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.SIMULAR_INATIV));
				System.out.println(I18N.getString(Messages.PEDE_OK));
				String res = scInativ.nextLine();
				
				break;
			}
			
			//Apagar inatividade
			case 8: {
				ArrayList<Inatividade> inatividades = inativBD.getInatividades();
				Scanner scDel = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.MOSTRA_INATIVIDADES));
				for (Inatividade i: inatividades) {
					System.out.println(i.toStringUI());
				}
				int idApagar = Integer.parseInt(scDel.nextLine());
				inativBD.delete(idApagar);
				System.out.println(I18N.getString(Messages.SUCESSO_INATV_APAGAR));
				break;
			}
			
			//Simular botao pressionado
			case 9: {
				Scanner scAtiv = new Scanner(System.in);
				System.out.println(I18N.getString(Messages.SIMULAR_BUTAO));
				System.out.println(I18N.getString(Messages.PEDE_OK));
				String res = scAtiv.nextLine();
				break;
			}
			
			//para terminar
			case 10: {
				acabou = true;
				break;
			}
			
			
			default: {
				System.out.println("Essa opcao nao estah disponivel!");
				break;
			}
				
			}
			
		}
		
		sc.close();
		System.exit(1);
		
	}

}
