package aviso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import atividade.Atividade;

public class AvisoBD {
	
	private static final String URL = "jdbc:sqlite:aviso.db";
	
	public AvisoBD() throws ClassNotFoundException {
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
		String query = "CREATE TABLE IF NOT EXISTS aviso"
				+ "(ID integer PRIMARY KEY autoincrement,"
				+ " MSG TEXT NOT NULL,"
				+ " DATA_INICIO TEXT NOT NULL,"
				+ " DATA_FIM TEXT NOT NULL,"
				+ " PERIODICIDADE TEXT NOT NULL);";
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			statement.execute(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}				
	}
	
	public void insert(String msg, String dataI, String dataF, String period) {
		String query = "INSERT INTO atividade"
				+ "(MSG, DATA_INICIO, DATA_FIM, PERIODICIDADE)"
				+ " VALUES('" + msg + "','" + dataI + "','" + dataF + "','" + period + "');";
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			statement.execute(query);
			System.out.println("Aviso inserido com sucesso!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Aviso> getAviso(){
		ResultSet res;
		String query = "SELECT * FROM aviso;";
		ArrayList<Aviso> avisos = null;
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			avisos = new ArrayList<Aviso>();
			res = statement.executeQuery(query);
			
			while (res.next()) {
				int id = res.getInt("ID");
				String msg = res.getString("MSG");
				String dataI = res.getString("DATA_INICIO");
				String dataF = res.getString("DATA_FIM");
				int period = res.getInt("PERIODICIDADE");
				
				Aviso a = new Aviso(id, msg, dataI, dataF, period);
				avisos.add(a);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return avisos;
		
	}

}
