package inatividade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import atividade.Atividade;

public class InatividadeBD {
	
	private static final String F_NOME = "inatividades.txt";
	private File inatividades;
	
	public InatividadeBD() throws ClassNotFoundException {
		inatividades = new File(F_NOME);
		criar();
	}

	public void criar() {
		try {
			if (!inatividades.exists()) {
				inatividades.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void insert(String dataI, String dataF, String duracao) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inatividades));
		int id = 0;
		String ln = null;
		while ((ln = br.readLine()) != null) {
			id = Integer.parseInt(ln.split("\\|")[0]);
		}
		br.close();
		
		id = id+1;

		BufferedWriter bw = new BufferedWriter(new FileWriter(inatividades, true));
		bw.write(id + "|" + dataI + "|" + dataF + "|" + duracao);
		bw.newLine();
		bw.flush();
		bw.close();
	}
	
	public ArrayList<Inatividade> getInatividades() throws NumberFormatException, IOException{
		ArrayList<Inatividade> res = new ArrayList<Inatividade>();

		BufferedReader br = new BufferedReader(new FileReader(inatividades));
		String ln = null;
		while ((ln = br.readLine()) != null) {
			String[] info = ln.split("\\|");
			int id = Integer.parseInt(info[0]);
			String dataI = info[1];
			String dataF = info[2];
			int duracao = Integer.parseInt(info[3]);
			res.add(new Inatividade(id, dataI, dataF, duracao));
		}
		br.close();

		return res;
		
	}

}
