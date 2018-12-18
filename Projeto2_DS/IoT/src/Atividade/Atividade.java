package Atividade;

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
	
	public Atividade(String dataInicio, String dataFim, String divisao) {
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
}
