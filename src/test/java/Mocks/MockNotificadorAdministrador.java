package Mocks;

import Observers.ObserverBusqueda;
import TpAnual.InfoFast;
import TpAnual.Resultado;

public class MockNotificadorAdministrador implements ObserverBusqueda{

	public boolean administradorNotificado;
	private long tiempoMaximoBusqueda;
    private InfoFast sistema;
	

	@Override
	public void notificarBusqueda(Resultado resultado) {
		this.administradorNotificado = true;	
	}
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	public void setSistema(InfoFast sistema) {
		this.sistema = sistema;
	}

}
