package monitorAtividade;

import static i18n.Messages.DEVICE_RUNNING;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import bd.AtividadeBD;
import i18n.I18N;

public class SensorAtividadeZirk {

	private Bezirk bezirk;
	private AtividadeBD aBD;

	public SensorAtividadeZirk() throws ClassNotFoundException {
		aBD = new AtividadeBD();
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Atividade Zirk");
		System.err.println("Instância de Bezirk apanhada");
	}

	public void sendAtividade() throws IOException {
		//produces some  values since this is a mock
		String[] divisoes = {"casa de banho", "quarto", "cozinha", "sala", "escritorio"};
		String[] horasOco = {"12:10", "14:15", "16:30", "17:50", "18:05"};
		String[] horasIni = {"00:00", "02:00", "05:00", "08:00", "10:00"};		
		String[] horasFin = {"19:00", "20:00", "21:00", "22:00", "23:00"};
		
        Random r = new Random();
        int randomDiv = r.nextInt(divisoes.length);
        int randomHorOco = r.nextInt(horasOco.length);		
		int randomHorIni = r.nextInt(horasIni.length);
		int randomHorFin = r.nextInt(horasFin.length);
		
		final AtividadeEvent atividadeEvent = new AtividadeEvent(divisoes[randomDiv], horasOco[randomHorOco]);
		aBD.insert(horasIni[randomHorIni], horasFin[randomHorFin], divisoes[randomDiv]);

		//sends the event
        bezirk.sendEvent(atividadeEvent);
        System.err.println(atividadeEvent.toString());
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		SensorAtividadeZirk atZ = new SensorAtividadeZirk();
		System.err.println("Este produto tem um Sensor de Movimento");

		System.out.println(I18N.getString(DEVICE_RUNNING, "Sensor de Movimento"));
		atZ.sendAtividade();
	}

}
