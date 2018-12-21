package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import atividade.AtividadeBD;
import aviso.Aviso;
import aviso.AvisoBD;
import contactos.Contacto;
import contactos.ContactoBD;
import dispositivoVestivel.RunnableDevice;
import i18n.I18N;
import i18n.Messages;
import inatividade.InatividadeBD;

public class UI {

	public static void main(String[] args) throws IOException {
		RunnableDevice rd = new RunnableDevice();
		start(rd);
	}

	private static void start(RunnableDevice rd) throws IOException {

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
			
			case 4: {
				break;
			}
			
			case 5: {
				break;
			}
			
			case 6: {
				break;
			}
			
			case 7: {
				break;
			}
			
			default: {
				break;
			}
				
			}
			
		}
		
		
		
	}

}
