package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

	private void criaTabela() {
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

}
