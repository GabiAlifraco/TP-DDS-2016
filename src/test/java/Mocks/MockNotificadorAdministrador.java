package Mocks;

import Requerimientos.ObserverBusqueda;
import TpAnual.Mapa;
import TpAnual.Resultado;

public class MockNotificadorAdministrador implements ObserverBusqueda{

	public boolean administradorNotificado;
	private long tiempoMaximoBusqueda;
    private Mapa sistema;
	

	@Override
	public void notificarBusqueda(Resultado resultado) {
		this.administradorNotificado = true;	
	}
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	public void setSistema(Mapa sistema) {
		this.sistema = sistema;
	}

}
