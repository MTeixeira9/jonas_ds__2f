package botao;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Aviso {
	
	private int id;
	private String msg;
	private String dataInicio;
	private String dataFim;
	private long periodicidade;
	
	public Aviso(int id, String msg, String dataInicio, String dataFim, long periodicidade) {
		this.id = id;
		this.msg = msg;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.periodicidade = periodicidade*60000;
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
	
	public long getPeriodicidade() {
		return periodicidade;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.id + "--->" + this.msg + " de " + this.dataInicio + " ate " + this.dataFim;
	}
	
	public String toStringTimer() {
		return this.id + "--->" + this.msg + " de " + this.dataInicio + " ate " + this.dataFim;
	}
	

	public boolean ehValido() throws ParseException {
		DateFormat format = new SimpleDateFormat("dd-MM-yy HH:mm");
		Date inicio = format.parse(dataInicio);
		Date fim = format.parse(dataFim);
		Date d = new Date();
		
		if(d.after(inicio) && d.before(fim)) {
			return true;
		}
		
		return false;
	}
	
}
