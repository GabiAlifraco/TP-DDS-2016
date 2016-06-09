package Requerimientos;

import TpAnual.Mapa;
import TpAnual.Resultado;

public class NotificadorAdministrador implements ObserverBusqueda{

	private long tiempoMaximoBusqueda;
    private Mapa sistema;
	
    @Override
	public void notificarBusqueda(Resultado resultado) {
		if(busquedaSuperaTiempoMaximoDemora(resultado.getSegundosBusqueda())){
			sendMail(sistema.getMailAdministrador());
		}
		
	}

	private void sendMail(String mailAdministrador) {
		
		System.out.println("Mail Enviado");
	}

	private boolean busquedaSuperaTiempoMaximoDemora(long tiempoBusqueda) {
		
		return tiempoBusqueda > this.getTiempoMaximoBusqueda();
	}

	public long getTiempoMaximoBusqueda() {
		return tiempoMaximoBusqueda;
	}

	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}

	public Mapa getSistema() {
		return sistema;
	}

	public void setSistema(Mapa sistema) {
		this.sistema = sistema;
	}

}
