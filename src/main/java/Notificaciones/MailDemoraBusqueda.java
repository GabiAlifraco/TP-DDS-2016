package Notificaciones;

import Reportes.ResultadosReportes;
import Resultado.Resultado;

public class MailDemoraBusqueda implements NotificacionBusqueda{

	private long tiempoMaximoBusqueda;
	private ResultadosReportes sistema;
    
	//En caso de que el tiempo que tarda en buscar supere al maximo estipulado, manda mail	
    @Override
	public void notificarBusqueda(Resultado resultado) {
		if(busquedaSuperaTiempoMaximoDemora(resultado.getSegundosBusqueda())){
			sendMail();
		}
	}
    
    //Simula mandar mail-Escribe por pantalla-->Para mas adelante
	private void sendMail() {
		System.out.println("Mail Enviado");
	}

	//Devuelve true si supera el maximo determinado por nosotros
	private boolean busquedaSuperaTiempoMaximoDemora(long tiempoBusqueda) {
		return tiempoBusqueda > this.getTiempoMaximoBusqueda();
	}
	
	//Getters
	public long getTiempoMaximoBusqueda() {
		return tiempoMaximoBusqueda;
	}
	
	public ResultadosReportes getSistema() {
		return sistema;
	}
	
	//Setters
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	
	public void setSistema(ResultadosReportes sistema) {
		this.sistema = sistema;
	}

}
