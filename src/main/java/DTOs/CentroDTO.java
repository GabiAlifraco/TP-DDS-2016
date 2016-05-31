package DTOs;

import java.util.ArrayList;
import java.util.List;

public class CentroDTO {

	private int numeroComuna;
	private String zonasIncluidas;
	private String director;
	private String domicilioCompletoCGP;
	private String telefonoCGP;
	private List<ServiciosDTO> serviciosCGP = new ArrayList<ServiciosDTO>();
	
	public int getNumeroComuna() {
		return numeroComuna;
	}
	public void setNumeroComuna(int numeroComuna) {
		this.numeroComuna = numeroComuna;
	}
	public String getZonasIncluidas() {
		return zonasIncluidas;
	}
	public void setZonasIncluidas(String zonasIncluidas) {
		this.zonasIncluidas = zonasIncluidas;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDomicilioCompletoCGP() {
		return domicilioCompletoCGP;
	}
	public void setDomicilioCompletoCGP(String domicilioCompletoCGP) {
		this.domicilioCompletoCGP = domicilioCompletoCGP;
	}
	public String getTelefonoCGP() {
		return telefonoCGP;
	}
	public void setTelefonoCGP(String telefonoCGP) {
		this.telefonoCGP = telefonoCGP;
	}
	public List<ServiciosDTO> getServiciosCGP() {
		return serviciosCGP;
	}
	public void setServiciosCGP(List<ServiciosDTO> serviciosCGP) {
		this.serviciosCGP = serviciosCGP;
	}
	
}
