package contactos;

public class Contacto {
	
	private int id;
	private String nome;
	private int numero;
	
	public Contacto(int id, String nome, int numero) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String toString() {
		return nome + " : " + numero;
	}

}
