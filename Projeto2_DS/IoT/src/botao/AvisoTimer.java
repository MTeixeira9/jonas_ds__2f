package botao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class AvisoTimer extends Thread{
	
	private Timer timer;
	private TimerTask tt;
	private Aviso aviso;
	
	
	public AvisoTimer (Aviso aviso) {
		timer = new Timer();
		this.aviso = aviso;
		tt = new TimerTask() {
			
			@Override
			public void run() {
				try {
					if(aviso.ehValido()) {
						Calendar c = Calendar.getInstance();
						System.err.println(c.get(Calendar.HOUR) +":" + c.get(Calendar.MINUTE) + ":" + 
								c.get(Calendar.SECOND)+ " - " +  aviso.getMsg());
					}else {
						stopTimer();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		timer.schedule(tt,0, TimeUnit.MINUTES.toMillis(aviso.getPeriodicidade()));
				
	}
	
	public void stopTimer() {
		timer.cancel();
		timer.purge();
	}
	
	

}
