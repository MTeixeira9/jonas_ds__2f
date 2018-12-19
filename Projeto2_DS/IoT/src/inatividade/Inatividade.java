package inatividade;

public class Inatividade {
	
	private int id;
	private String dataInicio;
	private String dataFim;
	private String periodo;
	
	
	public Inatividade(int id, String dataInicio, String dataFim, String periodo) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.periodo = periodo;
	}
	
	public String getDataInicio() {
		return dataInicio;
	}
	
	public String getDataFim() {
		return dataFim;
	}
	
	public String getPeriodo() {
		return periodo;
	}

}
