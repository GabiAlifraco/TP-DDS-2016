package Mocks;

import ConsultasPoi.ResultadosConsultasPoi;
import Requerimientos.NotificacionBusqueda;
import Resultado.Resultado;


public class MockNotificadorAdministrador implements NotificacionBusqueda{

	public boolean administradorNotificado;
	private long tiempoMaximoBusqueda;
    private ResultadosConsultasPoi sistema;
	

	@Override
	public void notificarBusqueda(Resultado resultado) {
		this.administradorNotificado = true;	
	}
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	public void setSistema(ResultadosConsultasPoi sistema) {
		this.sistema = sistema;
	}

}
