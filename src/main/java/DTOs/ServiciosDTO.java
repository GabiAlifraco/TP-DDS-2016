package DTOs;

import java.util.ArrayList;
import java.util.List;

public class ServiciosDTO {

	private String nombreServicio;
	private List<RangosServiciosDTO> rangosCGP = new ArrayList<RangosServiciosDTO>();
	
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public List<RangosServiciosDTO> getRangosCGP() {
		return rangosCGP;
	}
	public void setRangosCGP(List<RangosServiciosDTO> rangosCGP) {
		this.rangosCGP = rangosCGP;
	}
}
