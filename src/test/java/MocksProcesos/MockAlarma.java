package MocksProcesos;

import Procesos.PlanificadorProcesos;
import Procesos.Proceso;
import Procesos.Recordatorio;

public class MockAlarma extends Recordatorio {

	boolean alarmaSeteada = false;
	boolean procesoEjecutado = false;

	@Override
	public void run() {
		this.procesoEjecutado = true;
	}

	@Override
	public void setAlarma(Proceso proceso, PlanificadorProcesos planificadorProcesos) {
		this.alarmaSeteada = true;
	}
	
	public boolean alarmaSeteada(){
		return alarmaSeteada;
	}
}
