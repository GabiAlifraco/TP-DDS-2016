package Notificaciones;


import Reportes.ResultadosReportes;
import Resultado.Resultado;


public class MailDemoraBusqueda implements NotificacionBusqueda{

	private long tiempoMaximoBusqueda;
    private ResultadosReportes sistema;
	
    @Override
	public void notificarBusqueda(Resultado resultado) {
		if(busquedaSuperaTiempoMaximoDemora(resultado.getSegundosBusqueda())){
			sendMail();
		}
		
	}

	private void sendMail() {
		
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
