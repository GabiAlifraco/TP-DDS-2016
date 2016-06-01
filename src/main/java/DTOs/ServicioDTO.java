package DTOs;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {

	private String nombreServicio;
	private List<RangoServicioDTO> rangosDTO = new ArrayList<RangoServicioDTO>();
	
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public List<RangoServicioDTO> getRangosDTO() {
		return rangosDTO;
	}
	public void setRangosDTO(List<RangoServicioDTO> rangosCGP) {
		this.rangosDTO = rangosCGP;
	}
}
