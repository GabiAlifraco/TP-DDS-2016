package DTOs;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {

	private String nombreServicio;
	private RangoServicioDTO rangosDTO = new RangoServicioDTO();
	
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public RangoServicioDTO getRangosDTO() {
		return rangosDTO;
	}
	public void setRangosDTO(RangoServicioDTO rangosCGP) {
		this.rangosDTO = rangosCGP;
	}
	
}
