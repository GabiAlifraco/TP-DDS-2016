package Mocks;

import Notificaciones.NotificacionBusqueda;
import Reportes.ResultadosReportes;
import Resultado.Resultado;


public class MockNotificadorAdministrador implements NotificacionBusqueda{

	public boolean administradorNotificado;
	private long tiempoMaximoBusqueda;
    private ResultadosReportes sistema;
	

	@Override
	public void notificarBusqueda(Resultado resultado) {
		this.administradorNotificado = true;	
	}
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	public void setSistema(ResultadosReportes sistema) {
		this.sistema = sistema;
	}

}
