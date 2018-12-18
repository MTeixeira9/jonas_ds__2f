package core;

import java.sql.Date;

import com.bezirk.middleware.messages.Event;

public class Atividade extends Event{
	
	private int id;
	private Date dataInicio;
	private Date dataFim;
	private String divisao;
	
	public Atividade(Date dataInicio, Date dataFim, String divisao) {
		this.id = id;
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
