package Procesos;

import java.util.TimerTask;

public abstract class Recordatorio extends TimerTask{

	public abstract void setAlarma(Proceso proceso, PlanificadorProcesos planificadorProcesos);

}
