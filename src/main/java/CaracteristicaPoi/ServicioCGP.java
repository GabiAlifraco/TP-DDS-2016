package CaracteristicaPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ServicioCGP")
public class ServicioCGP {
	@Id
	@GeneratedValue
	private long servicioID;
	@Column(name="nombre")
	private String nombre;
	@OneToMany
	@JoinColumn(name="servicioID")
    private List<Disponibilidad> horariosDeAtencion;
	
	public ServicioCGP(String unNombre,List<Disponibilidad> horarios){
    	nombre=unNombre;
    	horariosDeAtencion= horarios;
    }
	
	public ServicioCGP() {
	}

	public void setHorariosDeAtencion(List<Disponibilidad> horariosDeAtencion) {
		this.horariosDeAtencion = horariosDeAtencion;
	}

	public boolean horarioDisponible(DayOfWeek dia, LocalTime hora) {
		return horariosDeAtencion.stream().anyMatch(disp -> disp.disponibleEnDiayHora(dia, hora));
	}
	//Este metodo no se esta utilizando
	public boolean igualNombre(String nombreServicio) {
		return this.nombre.equals(nombreServicio);
	}
	//Getters y Setters
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Disponibilidad> getHorariosDeAtencion() {
		return horariosDeAtencion;
	}


}