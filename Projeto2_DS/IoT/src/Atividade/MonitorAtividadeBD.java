package Atividade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonitorAtividadeBD {
	
	private static Matcher matcher;
	private static Pattern pattern;
	private static final String URL = "jdbc:sqlite:monitor_atividade.bd";
	
	public MonitorAtividadeBD() throws ClassNotFoundException {
		Connection con = null;
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(URL);
			criaTabela();
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void criaTabela() {
		String query = "CREATE TABLE IF NOT EXISTS atividade"
				+ "(ID integer PRIMARY KEY autoincrement,"
				+ " DATA_INICIO TEXT NOT NULL,"
				+ " DATA_FIM TEXT NOT NULL,"
				+ " DIVISAO TEXT NOT NULL);";
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			statement.execute(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}				
	}
	
	public void insert(String dataI, String dataF, String divisao) {
		String query = "INSERT INTO atividade"
				+ "(DATA_INICIO, DATA_FIM, DIVISAO)"
				+ " VALUES('" + dataI + "','" + dataF + "','" + divisao + "');";
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			statement.execute(query);
			System.out.println("Atividade inserida com sucesso!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Atividade> getAtividade(){
		ResultSet res;
		String query = "SELECT * FROM atividade;";
		ArrayList<Atividade> atividades = null;
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			atividades = new ArrayList<Atividade>();
			res = statement.executeQuery(query);
			
			while (res.next()) {
				int id = res.getInt("ID");
				String dataI = res.getString("DATA_INICIO");
				String dataF = res.getString("DATA_FIM");
				String divisao = res.getString("DIVISAO");
				
				Atividade a = new Atividade(dataI, dataF, divisao);
				atividades.add(a);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return atividades;
		
	}
	
	//TODO atividadesDetetadas

}
