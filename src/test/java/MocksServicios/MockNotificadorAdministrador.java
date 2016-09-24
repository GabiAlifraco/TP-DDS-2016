package MocksServicios;

import Notificaciones.NotificacionBusqueda;
import Resultado.Resultado;
import Terminal.Terminal;


public class MockNotificadorAdministrador implements NotificacionBusqueda{

	public boolean administradorNotificado;
	private long tiempoMaximoBusqueda;
   
	

	@Override
	public void actualizar(Resultado resultado,Terminal terminal) {
		if(resultado.getSegundosBusqueda() > tiempoMaximoBusqueda){
			this.administradorNotificado = true;
		}
	}
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	

}
