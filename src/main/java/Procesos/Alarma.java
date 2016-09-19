package Procesos;

public class Alarma extends Recordatorio {

	private Proceso procesoAEjecutar;
	private PlanificadorProcesos planificadorANotificar;

	public void setAlarma(Proceso proceso, PlanificadorProcesos planificador) {
		this.procesoAEjecutar = proceso;
		this.planificadorANotificar = planificador;
	}

	@Override
	public void run() {

		planificadorANotificar.ejecutarProceso(procesoAEjecutar);

	}
}
