package DTOs;

import java.util.ArrayList;
import java.util.List;

public class ServiciosDTO {

	private String nombreServicio;
	private List<RangosServiciosDTO> rangosDTO = new ArrayList<RangosServiciosDTO>();
	
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public List<RangosServiciosDTO> getRangosDTO() {
		return rangosDTO;
	}
	public void setRangosDTO(List<RangosServiciosDTO> rangosCGP) {
		this.rangosDTO = rangosCGP;
	}
}
