package Observers;

import TpAnual.InfoFast;
import TpAnual.Resultado;

public class ObserverDemoraBusqueda implements ObserverBusqueda{

	private long tiempoMaximoBusqueda;
    private InfoFast sistema;
	
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

	public InfoFast getSistema() {
		return sistema;
	}

	public void setSistema(InfoFast sistema) {
		this.sistema = sistema;
	}

}
