package aviso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import contactos.Contacto;
import monitorAtividade.Atividade;

public class AvisoBD {
	
	private static final String F_NOME = "avisos.txt";
	private File avisos;
	
	public AvisoBD() throws ClassNotFoundException {
		avisos = new File(F_NOME);
		criar();
			
		
	}

	public void criar() {
		
		try {
			if (!avisos.exists()) {
				avisos.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insert(String msg, String dataI, String dataF, String period) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(avisos));
		int id = 0;
		String ln = null;
		while ((ln = br.readLine()) != null) {
			id = Integer.parseInt(ln.split("\\|")[0]);
		}
		br.close();
		
		id = id+1;

		BufferedWriter bw = new BufferedWriter(new FileWriter(avisos, true));
		bw.write(id + "|" + msg + "|" + dataI + "|" + dataF + "|" + period);
		bw.newLine();
		bw.flush();
		bw.close();
		
	}
	
	public ArrayList<Aviso> getAvisos() throws IOException{
		
		ArrayList<Aviso> res = new ArrayList<Aviso>();

		BufferedReader br = new BufferedReader(new FileReader(avisos));
		String ln = null;
		while ((ln = br.readLine()) != null) {
			String[] info = ln.split("\\|");
			int id = Integer.parseInt(info[0]);
			String msg = info[1];
			String dataI = info[2];
			String dataF = info[3];
			int period = Integer.parseInt(info[4]);
			res.add(new Aviso(id, msg, dataI, dataF,period ));
		}
		br.close();

		return res;
		
	}

	
	public void delete(int idApagar) throws IOException {
		BufferedReader brD = new BufferedReader(new FileReader(avisos));
		File temp = new File (avisos.getAbsolutePath() + ".tmp");
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
		avisos.delete();
		temp.renameTo(avisos);
			
	}

}
