package inatividade;

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
	
	//private static Matcher matcher;
	//private static Pattern pattern;
	private static final String URL = "jdbc:sqlite:inatividade.db";
	
	public InatividadeBD() throws ClassNotFoundException {
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
		String query = "CREATE TABLE IF NOT EXISTS inatividade"
				+ "(ID integer PRIMARY KEY autoincrement,"
				+ " DATA_INICIO TEXT NOT NULL,"
				+ " DATA_FIM TEXT NOT NULL,"
				+ " PERIODO TEXT NOT NULL);";
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			statement.execute(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}				
	}
	
	public void insert(String dataI, String dataF, String divisao) {
		String query = "INSERT INTO inatividade"
				+ "(DATA_INICIO, DATA_FIM, PERIODO)"
				+ " VALUES('" + dataI + "','" + dataF + "','" + divisao + "');";
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			statement.execute(query);
			System.out.println("Inatividade inserida com sucesso!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Inatividade> getInatividades(){
		ResultSet res;
		String query = "SELECT * FROM inatividade;";
		ArrayList<Inatividade> inatividades = null;
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			inatividades = new ArrayList<Inatividade>();
			res = statement.executeQuery(query);
			
			while (res.next()) {
				int id = res.getInt("ID");
				String dataI = res.getString("DATA_INICIO");
				String dataF = res.getString("DATA_FIM");
				String periodo = res.getString("PERIODO");
				
				Inatividade i = new Inatividade(id, dataI, dataF, periodo);
				inatividades.add(i);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return inatividades;
		
	}

}
