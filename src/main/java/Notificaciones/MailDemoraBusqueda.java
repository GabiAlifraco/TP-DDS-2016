package Notificaciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import Resultado.Resultado;
import Terminal.Terminal;
@Entity  
@Table(name="MailsDemora")  
public class MailDemoraBusqueda extends NotificacionBusqueda{

	@Transient
	private boolean mailEnviado;
	@Id
	@GeneratedValue
	@Column(name="MailDemora_Id")
	private long id_MailDemora;
	
	@Column(name="TiempoMaximoBusqueda")
	private long tiempoMaximoBusqueda;
    
	@OneToMany(mappedBy="mail")
	private List<Terminal> terminales = new ArrayList<Terminal>();
	
	public MailDemoraBusqueda() {
	}
	//En caso de que el tiempo que tarda en buscar supere al maximo estipulado, manda mail	
    @Override
	public void actualizar(Resultado resultado,Terminal terminal) {
		if(busquedaSuperaTiempoMaximoDemora(resultado.getSegundosBusqueda())){
			sendMail();
		}
	}
    
    //Simula mandar mail-Escribe por pantalla-->Para mas adelante
	private void sendMail() {
		this.mailEnviado = true;
	}

	//Devuelve true si supera el maximo determinado por nosotros
	private boolean busquedaSuperaTiempoMaximoDemora(long tiempoBusqueda) {
		return tiempoBusqueda > this.getTiempoMaximoBusqueda();
	}
	
	//Getters
	public long getTiempoMaximoBusqueda() {
		return tiempoMaximoBusqueda;
	}
	
	public long getId_MailDemora() {
		return id_MailDemora;
	}
	
	
	//Setters
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		this.tiempoMaximoBusqueda = tiempoMaximoBusqueda;
	}
	public void setId_MailDemora(long id_MailDemora) {
		this.id_MailDemora = id_MailDemora;
	}
}
