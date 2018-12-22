package contactos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AvisoContactos {
	
	private ArrayList<Contacto> contactos;
	private static final String DATE_FROMAT = "dd-MM-yy HH:mm";
	
	public void mandarSMSparaContactos(ContactoBD bd, String msg) throws NumberFormatException, IOException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FROMAT);
		String actualTime = sdf.format(cal.getTime());
		
		contactos = bd.getContactos();
		for(Contacto c : contactos) {
			System.out.println(actualTime + " - Mensagem enviada para " + c.getNome()
					+ " nr:" + c.getNumero() + " msg: " + msg);
			
		}
	}

}
