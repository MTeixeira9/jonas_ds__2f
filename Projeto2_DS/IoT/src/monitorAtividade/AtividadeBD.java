package monitorAtividade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AtividadeBD {
	
	private static final String F_NOME = "atividades.txt";
	private File atividades;
	
	public AtividadeBD() throws ClassNotFoundException {
		
		atividades = new File(F_NOME);
		criar();
	}

	public void criar() {
		try {
			if (!atividades.exists()) {
				atividades.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void insert(String dataI, String dataF, String divisao) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(atividades));
		int id = 0;
		String ln = null;
		while ((ln = br.readLine()) != null) {
			id = Integer.parseInt(ln.split("\\|")[0]);
		}
		br.close();
		
		id = id+1;

		BufferedWriter bw = new BufferedWriter(new FileWriter(atividades, true));
		bw.write(id + "|" + dataI + "|" + dataF + "|" + divisao);
		bw.newLine();
		bw.flush();
		bw.close();
		
	}
	
	public ArrayList<Atividade> getAtividades() throws IOException{
		
		ArrayList<Atividade> res = new ArrayList<Atividade>();

		BufferedReader br = new BufferedReader(new FileReader(atividades));
		String ln = null;
		while ((ln = br.readLine()) != null) {
			String[] info = ln.split("\\|");
			int id = Integer.parseInt(info[0]);
			String dataI = info[1];
			String dataF = info[2];
			String divisao = info[3];
			res.add(new Atividade(id, dataI, dataF, divisao));
		}
		br.close();

		return res;
		
		
	}

	public void delete(int idApagar) throws IOException {
		BufferedReader brD = new BufferedReader(new FileReader(atividades));
		File temp = new File (atividades.getAbsolutePath() + ".tmp");
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
		atividades.delete();
		temp.renameTo(atividades);
			
	}
	
	

}
