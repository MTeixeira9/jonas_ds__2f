package core;

import java.sql.Date;

public class Aviso {
	
	private int id;
	private String msg;
	private Date dataInicio;
	private Date dataFim;
	private int periodicidade;
	
	public Aviso(String msg, Date dataInicio, Date dataFim, int periodicidade) {
		this.msg = msg;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.periodicidade = periodicidade;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public Date getDataFim() {
		return dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	
	public int getPeriodicidade() {
		return periodicidade;
	}
	
}
