package monitorAtividade;

import static i18n.Messages.DEVICE_RUNNING;

import java.io.IOException;
import java.util.Random;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import i18n.I18N;

public class SensorInatividadeZirk {

	private Bezirk bezirk;
	private InatividadeBD iBD;

	public SensorInatividadeZirk() throws ClassNotFoundException {
		iBD = new InatividadeBD();
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Inatividade Zirk");
		System.err.println("Instância de Bezirk apanhada");
	}

	public void sendInatividade() throws IOException {
		//produces some  values since this is a mock
		String[] horasIni = {"00:00", "02:00", "05:00", "08:00", "10:00"};		
		String[] horasFin = {"19:00", "20:00", "21:00", "22:00", "23:00"};
		final String duracao = "45";
		
        Random r = new Random();
        int randomHorIni = r.nextInt(horasIni.length);
		int randomHorFin = r.nextInt(horasFin.length);
		
		final InatividadeEvent inatividadeEvent = new InatividadeEvent(horasIni[randomHorIni], 
				horasFin[randomHorFin], duracao);
		iBD.insert(horasIni[randomHorIni], horasFin[randomHorFin], duracao);
		
		//sends the event
        bezirk.sendEvent(inatividadeEvent);
        System.err.println(inatividadeEvent.toString());
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		SensorInatividadeZirk atZ = new SensorInatividadeZirk();
		System.err.println("Este produto tem um Sensor de Movimento");

		System.out.println(I18N.getString(DEVICE_RUNNING, "Sensor de Movimento"));
		atZ.sendInatividade();
	}
	
}
