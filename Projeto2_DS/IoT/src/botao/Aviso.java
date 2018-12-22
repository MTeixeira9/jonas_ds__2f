package botao;

import java.sql.Date;

public class Aviso {
	
	private int id;
	private String msg;
	private String dataInicio;
	private String dataFim;
	private int periodicidade;
	
	public Aviso(int id, String msg, String dataInicio, String dataFim, int periodicidade) {
		this.id = id;
		this.msg = msg;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.periodicidade = periodicidade;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public String getDataFim() {
		return dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}
	
	public int getPeriodicidade() {
		return periodicidade;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.id + "--->" + this.msg + " de " + this.dataInicio + " ate " + this.dataFim;
	}
	
}
