package atividade;

import static i18n.Messages.DEVICE_RUNNING;

import java.util.Random;
import java.util.Scanner;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import i18n.I18N;

public class SensorAtividadeZirk {

	private Bezirk bezirk;

	public SensorAtividadeZirk() {
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Atividade Zirk");
		System.err.println("Instância de Bezirk apanhada");
	}

	public void sendAtividade() {
		//produces some  values since this is a mock
		String[] divisoes = {"casa de banho", "quarto", "cozinha", "sala", "escritorio"};
		String[] horas = {"09:30", "12:00", "14:15", "16:30", "03:00", "21:50"};
        Random r = new Random();
        int randomDiv = r.nextInt(divisoes.length);
        int randomHor = r.nextInt(horas.length);
		final AtividadeEvent atividadeEvent = new AtividadeEvent(divisoes[randomDiv], horas[randomHor]);

		//sends the event
        bezirk.sendEvent(atividadeEvent);
        System.err.println(atividadeEvent.toString());
	}

	public static void main(String[] args) {
		SensorAtividadeZirk atZ = new SensorAtividadeZirk();
		System.err.println("Este produto tem um Sensor de Movimento");

		System.out.println(I18N.getString(DEVICE_RUNNING, "Sensor de Movimento"));
		atZ.sendAtividade();
	}

}
