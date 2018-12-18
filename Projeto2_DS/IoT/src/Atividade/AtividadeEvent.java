package Atividade;

import com.bezirk.middleware.messages.Event;

public class AtividadeEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String divisao;
	private String hora;
	
	public AtividadeEvent(String divisao, String hora) {
		this.divisao = divisao;
		this.hora = hora;
	}

	public String getDivisao() {
		return divisao;
	}

	public String getHora() {
		return hora;
	}
	
	public String toString() {
		return "Deteção de atividade - " + divisao + " - " + hora;
	}

}
