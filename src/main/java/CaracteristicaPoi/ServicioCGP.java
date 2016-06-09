package CaracteristicaPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;



public class ServicioCGP {
	private String nombre;
    private List<Disponibilidad> horariosDeAtencion;
	
	public ServicioCGP(String unNombre,List<Disponibilidad> horarios){
    	nombre=unNombre;
    	horariosDeAtencion= horarios;
    	
    }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Disponibilidad> getHorariosDeAtencion() {
		return horariosDeAtencion;
	}

	public boolean igualNombre(String nombreServicio) {
		return this.nombre.equals(nombreServicio);
	}
	public String getNombre() {
		return nombre;
	}

	public boolean horarioDisponible(DayOfWeek dia, LocalTime hora) {
		return horariosDeAtencion.stream().anyMatch(disp -> disp.disponibleEnDiayHora(dia, hora));
	}


}