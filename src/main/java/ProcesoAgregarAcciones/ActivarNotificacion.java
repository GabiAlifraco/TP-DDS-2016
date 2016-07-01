package ProcesoAgregarAcciones;

import Notificaciones.MailDemoraBusqueda;
import Terminal.Terminal;

public class ActivarNotificacion implements Accion{

	private long tiempoMaximoBusqueda;
	
	public ActivarNotificacion(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	
	@Override
	public void modificarConfiguracion(Terminal terminal) {
		if (terminal.getObserverBusquedas().size() == 0){
			MailDemoraBusqueda mailNotificacionBusqueda = new MailDemoraBusqueda(); 
			mailNotificacionBusqueda.setTiempoMaximoBusqueda(tiempoMaximoBusqueda);
		} else {
			terminal.getObserverBusquedas().stream()
			.forEach(notificador -> notificador.setTiempoMaximoBusqueda(tiempoMaximoBusqueda));
		}
	}

	
}
