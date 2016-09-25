package Notificaciones;

import Resultado.Resultado;
import Terminal.Terminal;

public class MailDemoraBusqueda implements NotificacionBusqueda{

	private long tiempoMaximoBusqueda;
    
	//En caso de que el tiempo que tarda en buscar supere al maximo estipulado, manda mail	
    @Override
	public void actualizar(Resultado resultado,Terminal terminal) {
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
	//Setters
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}

}
