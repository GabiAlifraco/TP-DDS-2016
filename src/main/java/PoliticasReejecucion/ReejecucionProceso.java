package PoliticasReejecucion;

import Procesos.Proceso;

public class ReejecucionProceso implements Politica{

	private int cantidadReejecucionesDefinidas;
	private int cantidadReejecucionesRealizadas = 0;
	private boolean notificarAdministrador;

	public ReejecucionProceso(int cantidadReejecuciones, boolean notificar) {
		this.cantidadReejecucionesDefinidas = cantidadReejecuciones;
		this.notificarAdministrador = notificar;
	}
	
	@Override
	public void aplicarPolitica(Proceso proceso) {
		if (cantidadReejecucionesDefinidas >= cantidadReejecucionesRealizadas){
			proceso.ejecutar();
			cantidadReejecucionesRealizadas++;
		} else {
			cantidadReejecucionesRealizadas = 0;
			notificarAdministrador(proceso);
		}
	}
	
	private void notificarAdministrador(Proceso proceso){
		if (notificarAdministrador){
			NotificacionFalloProceso notificar = new NotificacionFalloProceso();
			notificar.aplicarPolitica(proceso);
		}
	}

	public int getCantidadReejecucionesRealizadas() {
		return cantidadReejecucionesRealizadas;
	}
}
