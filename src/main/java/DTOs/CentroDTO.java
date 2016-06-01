package DTOs;

import java.util.ArrayList;
import java.util.List;

public class CentroDTO {

	private int numeroComuna;
	private String zonasIncluidas;
	private String director;
	private String domicilioCompletoDTO;
	private String telefonoDTO;
	private List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
	
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
		return domicilioCompletoDTO;
	}
	public void setDomicilioCompletoCGP(String domicilioCompletoCGP) {
		this.domicilioCompletoDTO = domicilioCompletoCGP;
	}
	public String getTelefonoCGP() {
		return telefonoDTO;
	}
	public void setTelefonoCGP(String telefonoCGP) {
		this.telefonoDTO = telefonoCGP;
	}
	public List<ServicioDTO> getServiciosDTO() {
		return serviciosDTO;
	}
	public void setServiciosDTO(List<ServicioDTO> serviciosDTO) {
		this.serviciosDTO = serviciosDTO;
	}
	
}
