package contactos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ContactoBD {
	private static final String F_NOME = "contactos.txt";
	private File contactos;

	public ContactoBD() {
		contactos = new File(F_NOME);
		criar();
	}

	public void criar() {

		try {
			if (!contactos.exists()) {
				contactos.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insert(String nome, String numero) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(contactos));
		int id = 0;
		while (br.readLine() != null) {
			id++;
		}
		br.close();

		BufferedWriter bw = new BufferedWriter(new FileWriter(contactos, true));
		bw.write(id + "|" + nome + "|" + numero);
		bw.newLine();
		bw.flush();
		bw.close();

	}

	public ArrayList<Contacto> getContactos() throws NumberFormatException, IOException{
		ArrayList<Contacto> res = new ArrayList<Contacto>();

		BufferedReader br = new BufferedReader(new FileReader(contactos));
		String ln = null;
		while ((ln = br.readLine()) != null) {
			String[] info = ln.split("\\|");
			int id = Integer.parseInt(info[0]);
			String nome = info[1];
			int numero = Integer.parseInt(info[2]);
			res.add(new Contacto(id, nome, numero));
		}
		br.close();

		return res;

	}
	
	public void delete(int idApagar) throws IOException {
		BufferedReader brD = new BufferedReader(new FileReader(contactos));
		File temp = new File (contactos.getAbsolutePath() + ".tmp");
		PrintWriter pw = new PrintWriter(new FileWriter(temp));
		String ln = null;
		
		while((ln = brD.readLine()) != null) {
			
			String [] split = ln.split("\\|");
			int id = Integer.parseInt(split[0]);
			
			if(id != idApagar) {
				pw.println(ln);
				pw.flush();
			}
			
		}
		pw.close();
		brD.close();
		contactos.delete();
		temp.renameTo(contactos);
			
	}

}
