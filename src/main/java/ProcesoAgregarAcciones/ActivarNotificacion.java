package ProcesoAgregarAcciones;

import Notificaciones.MailDemoraBusqueda;
import Notificaciones.NotificacionBusqueda;
import Terminal.Terminal;

public class ActivarNotificacion implements Accion {

	private NotificacionBusqueda mailDemoraBusqueda;

	public ActivarNotificacion(NotificacionBusqueda mailDemoraBusqueda) {
		this.mailDemoraBusqueda = mailDemoraBusqueda;
	}

	@Override
	public void modificarConfiguracion(Terminal terminal) {
		if (!terminal.getNotificadoresBusqueda().contains(MailDemoraBusqueda.class)) {
			terminal.agregarObserver(mailDemoraBusqueda);
		}
	}

}
