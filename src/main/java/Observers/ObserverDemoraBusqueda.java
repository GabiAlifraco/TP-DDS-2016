package Observers;

import TpAnual.InfoFast;
import TpAnual.Resultado;

public class ObserverDemoraBusqueda implements ObserverBusqueda{

	private long tiempoMaximoBusqueda;
    private InfoFast sistema;
    private Resultado resultado;
	@Override
	public void notificarBusqueda() {
		if(busquedaSuperaTiempoMaximoDemora()){
			sistema.getMailAdministrador();
		}
		
	}

	private boolean busquedaSuperaTiempoMaximoDemora() {
		
		return resultado.getSegundosBusqueda() > this.getTiempoMaximoBusqueda();
	}

	public long getTiempoMaximoBusqueda() {
		return tiempoMaximoBusqueda;
	}

	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}

	public InfoFast getSistema() {
		return sistema;
	}

	public void setSistema(InfoFast sistema) {
		this.sistema = sistema;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
}
