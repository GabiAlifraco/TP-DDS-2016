package Requerimientos;


import EstadisticasReportes.ResultadosReportes;
import Resultado.Resultado;


public class NotificarAdministrador implements NotificacionBusqueda{

	private long tiempoMaximoBusqueda;
    private ResultadosReportes sistema;
	
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

	public ResultadosReportes getSistema() {
		return sistema;
	}

	public void setSistema(ResultadosReportes sistema) {
		this.sistema = sistema;
	}

}
