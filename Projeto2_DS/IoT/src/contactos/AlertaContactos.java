package contactos;

import java.io.IOException;
import java.util.ArrayList;

public class AlertaContactos {
	
	private ArrayList<Contacto> contactos;
	
	public void mandarSMSparaContactos(ContactoBD bd, String msg) throws NumberFormatException, IOException {
		contactos = bd.getContactos();
		for(Contacto c : contactos) {
			System.out.println("Mensagem enviada para " + c.getNome() + " nr:" + c.getNumero() + " msg: " + msg);
			
		}
	}

}
