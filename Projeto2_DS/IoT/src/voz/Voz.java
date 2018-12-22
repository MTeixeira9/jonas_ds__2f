package voz;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Voz {

	//nome da voz que vamos usar
	private String nome; 
	
	//criar instancia de Voice
	private Voice voz;
	
	public Voz(String nome) {
		System.setProperty("mbrola.base", "mbrola2");
		this.nome = nome;
		this.voz = VoiceManager.getInstance().getVoice(this.nome);
		this.voz.allocate();
	}
	
	public void falar(String texto) {
		this.voz.speak(texto);
	}
}
