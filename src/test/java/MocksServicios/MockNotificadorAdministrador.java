package MocksServicios;

import Notificaciones.NotificacionBusqueda;
import Resultado.Resultado;


public class MockNotificadorAdministrador implements NotificacionBusqueda{

	public boolean administradorNotificado;
	private long tiempoMaximoBusqueda;
   
	

	@Override
	public void notificarBusqueda(Resultado resultado) {
		if(resultado.getSegundosBusqueda() > tiempoMaximoBusqueda){
			this.administradorNotificado = true;
		}
	}
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	

}
