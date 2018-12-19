package atividade;

import static i18n.Messages.DEVICE_RUNNING;

import java.util.Scanner;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import i18n.I18N;

public class AtividadeZirk {

	private Bezirk bezirk;

	public AtividadeZirk() {
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Atividade Zirk");
	}

	public void sendAtividade(String divisao, String hora) {
		final AtividadeEvent atividadeEvent = new AtividadeEvent(divisao, hora);

		//sends the event
        bezirk.sendEvent(atividadeEvent);
        System.err.println(atividadeEvent.toString());
	}

	public static void main(String[] args) {
		AtividadeZirk atZ = new AtividadeZirk();
		System.err.println("Este produto tem um Sensor de Movimento");

		System.out.println(I18N.getString(DEVICE_RUNNING, "Sensor de Movimento"));

		Scanner sc = new Scanner(System.in);
		System.out.println("Em que divisão foi detetado o movimento?");
		String divisao = sc.nextLine();
		System.out.println("A que horas ocorreu? [hh:mm]");
		String hora = sc.nextLine();
		sc.close();
		atZ.sendAtividade(divisao, hora);
	}

	/*
	 public void sendAirQualityUpdate() {
    	//produces some  values since this is a mock
        final double humidity = 0.8;
        final int dustLevel = 30;
        final int pollenLevel = 1000;
        final AirQualityUpdateEvent airQualityUpdateEvent = new AirQualityUpdateEvent(humidity, dustLevel, pollenLevel);

        //sends the event
        bezirk.sendEvent(airQualityUpdateEvent);
        System.err.println("Published air quality update: " + airQualityUpdateEvent.toString());
    }
	 */

}
