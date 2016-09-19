package Procesos;

import java.util.Date;
import java.util.Timer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlanificadorProcesos {
	
	protected Lock mutex = new ReentrantLock();
	private Recordatorio alarmaProceso;
	private Timer timerEjecucion;
	
	public void planificarProceso(Proceso proceso){
		
		alarmaProceso.setAlarma(proceso, this);
		Date horaEjecucion = proceso.getCronograma();
		timerEjecucion.schedule(alarmaProceso, horaEjecucion);
	}

	public void ejecutarProceso(Proceso procesoAEjecutar) {
		mutex.lock();
		procesoAEjecutar.ejecutar();
		mutex.unlock();
	}
	
	public void setAlarmaProceso(Recordatorio alarma){
		this.alarmaProceso = alarma;
	}
	
	public void setTimer(Timer timer){
		this.timerEjecucion = timer;
	}
	
}
