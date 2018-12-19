package contactos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import atividade.Atividade;

public class ContactoBD {
	private static final String URL = "jdbc:sqlite:contacto.db";
	
	public ContactoBD() throws ClassNotFoundException {
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
		String query = "CREATE TABLE IF NOT EXISTS contacto"
				+ "(ID integer PRIMARY KEY autoincrement,"
				+ " NOME TEXT NOT NULL,"
				+ " NUMERO TEXT NOT NULL);";
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			statement.execute(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}				
	}
	
	public void insert(String nome, String numero) {
		String query = "INSERT INTO contacto"
				+ "(NOME, NUMERO)"
				+ " VALUES('" + nome + "','" + numero +"');";
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			statement.execute(query);
			System.out.println("Contacto inserida com sucesso!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Contacto> getContactos(){
		ResultSet res;
		String query = "SELECT * FROM contacto;";
		ArrayList<Contacto> contactos = null;
		
		try (Connection con = DriverManager.getConnection(URL);
				Statement statement = con.createStatement()){
			contactos = new ArrayList<Contacto>();
			res = statement.executeQuery(query);
			
			while (res.next()) {
				int id = res.getInt("ID");
				String nome = res.getString("NOME");
				int numero = res.getInt("NUMERO");
				
				Contacto c = new Contacto(id, nome, numero);
				contactos.add(c);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return contactos;
		
	}

}
