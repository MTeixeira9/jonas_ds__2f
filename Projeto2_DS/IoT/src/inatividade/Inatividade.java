package inatividade;

public class Inatividade {
	
	private int id;
	private String dataInicio;
	private String dataFim;
	private int duracao;
	
	
	public Inatividade(int id, String dataInicio, String dataFim, int duracao) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.duracao = duracao;
	}
	
	public String getDataInicio() {
		return dataInicio;
	}
	
	public String getDataFim() {
		return dataFim;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	@Override
	public String toString() {
		return "inatividade durante " + duracao + " mn no periodo " + "[" + dataInicio + "," + dataFim + "]";
	}

}
