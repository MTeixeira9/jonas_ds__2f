package monitorAtividade;

import com.bezirk.middleware.messages.Event;

public class InatividadeEvent extends Event {

	private static final long serialVersionUID = 1L;
	private String horaInicio;
	private String horaFim;
	private String duracao;
	
	public InatividadeEvent(String horaInicio, String horaFim, String duracao) {
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.duracao = duracao;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}
	
	public String getDuracao() {
		return duracao;
	}
	
	public String toString() {
		return "Inatividade durante " + duracao + " minutos no periodo [" 
				+ horaInicio + "," + horaFim + "]";
	}
	

}
