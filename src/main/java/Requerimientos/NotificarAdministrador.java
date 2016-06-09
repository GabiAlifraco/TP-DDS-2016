package Requerimientos;


import ConsultasPoi.ResultadosConsultasPoi;
import Resultado.Resultado;


public class NotificarAdministrador implements NotificacionBusqueda{

	private long tiempoMaximoBusqueda;
    private ResultadosConsultasPoi sistema;
	
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

	public ResultadosConsultasPoi getSistema() {
		return sistema;
	}

	public void setSistema(ResultadosConsultasPoi sistema) {
		this.sistema = sistema;
	}

}
