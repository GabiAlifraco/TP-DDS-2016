package PoliticasReejecucion;

import Procesos.Proceso;

public class NotificacionFalloProceso implements Politica{

	public boolean administradorNotificado = false; 
	
	@Override
	public void aplicarPolitica(Proceso proceso) {

		this.notificarAdministrador(proceso);
		
	}

	private void notificarAdministrador(Proceso proceso) {
		this.administradorNotificado = true;
	}

}
