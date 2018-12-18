package core;

public class Contacto {
	
	private int id;
	private String nome;
	private int numero;
	
	public Contacto(String nome, int numero) {
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
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String toString() {
		return nome + " : " + numero;
	}

}
