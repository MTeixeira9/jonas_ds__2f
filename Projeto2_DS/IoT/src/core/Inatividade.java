package core;

import java.sql.Date;

public class Inatividade {
	
	private Date dataInicio;
	private Date dataFim;
	private String divisao;
	private int id;
	
	private boolean alerta = false;
	
	
	public Inatividade(Date dataInicio, Date dataFim, String divisao) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.divisao = divisao;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	
	public Date getDataFim() {
		return dataFim;
	}
	
	public String getDivisao() {
		return divisao;
	}

}
