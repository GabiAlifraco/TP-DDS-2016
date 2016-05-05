package TpAnual;

import java.time.LocalTime;
import java.util.List;

public class ServicioCGP {
	private String nombre;
	private List<String> diasDeAtencion;
    private Disponibilidad horarioDeAtencion;
	public List<String> palabrasClave;
    
    public String getNombre() {
		return nombre;
	}

	public ServicioCGP(String unNombre, List<String> unosDiasDeAtencion, Disponibilidad unaDisponibilidadHoraria,List<String> palabrasClave){
    	nombre=unNombre;
    	diasDeAtencion=unosDiasDeAtencion;
    	horarioDeAtencion=unaDisponibilidadHoraria;
    	this.palabrasClave = palabrasClave;
    }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDiasDeAtencion(List<String> diasDeAtencion) {
		this.diasDeAtencion = diasDeAtencion;
	}

	public void setHorarioDeAtencion(Disponibilidad horarioDeAtencion) {
		this.horarioDeAtencion = horarioDeAtencion;
	}

	public List<String> getDiasDeAtencion() {
		return diasDeAtencion;
	}
	
	public List<String> getPalabrasClave() {
		return palabrasClave;
	}

	public boolean horarioDentroDelRango(String hora){
		return (horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora)) && horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora)));
	}

	public boolean igualNombre(String nombreServicio) {
		return this.nombre.equals(nombreServicio);
	}
	

}
