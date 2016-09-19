package MocksProcesos;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MockTimer extends Timer {

	boolean alarmaPlanificada = false;
	
	@Override 
	public void schedule(TimerTask alarma, Date hora){
		this.alarmaPlanificada = true;
	}

	public boolean alarmaPlanificada() {
		return alarmaPlanificada;
	}
}
