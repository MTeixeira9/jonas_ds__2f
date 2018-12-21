package atividade;

import com.bezirk.middleware.messages.Event;

public class Atividade extends Event{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String dataInicio;
	private String dataFim;
	private String divisao;
	
	public Atividade(int id, String dataInicio, String dataFim, String divisao) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.divisao = divisao;
	}
	
	public String getDataInicio() {
		return dataInicio;
	}
	
	public String getDataFim() {
		return dataFim;
	}
	
	public String getDivisao() {
		return divisao;
	}
	
	@Override
	public String toString() {
	
		return "deteção de atividade na " + divisao + " no periodo " + "[" + dataInicio + "," + dataFim + "]";
	}
}
