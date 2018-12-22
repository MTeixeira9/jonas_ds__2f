package voz;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Voz {

	private VoiceManager vm;	
	//criar instancia de Voice
	private Voice voz;
	
	public Voz() {
		System.setProperty("mbrola.base", "mbrola2");
		this.vm = VoiceManager.getInstance();
		this.voz = vm.getVoice("mbrola_us1");
		voz.allocate();
	}
	
	public void falar(String texto) {
		voz.speak(texto);
	}
}
