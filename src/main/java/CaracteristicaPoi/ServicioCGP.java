package CaracteristicaPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.bson.types.ObjectId;

@javax.persistence.Entity
@javax.persistence.Table(name = "ServiciosCGP")
public class ServicioCGP {
	
	@org.mongodb.morphia.annotations.Id
	ObjectId id;
	
	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	@org.mongodb.morphia.annotations.Transient
	private long idServicioCGP;
	
	@javax.persistence.Column(name = "nombre")
	private String nombre;
	
	@javax.persistence.OneToMany
	@javax.persistence.OrderColumn(name="horarios")
	private List<Disponibilidad> horariosDeAtencion;

	public ServicioCGP(String unNombre, List<Disponibilidad> horarios) {
		nombre = unNombre;
		horariosDeAtencion = horarios;
    }
	
	public ServicioCGP() {
	}

	public void setHorariosDeAtencion(List<Disponibilidad> horariosDeAtencion) {
		this.horariosDeAtencion = horariosDeAtencion;
	}

	public boolean horarioDisponible(DayOfWeek dia, LocalTime hora) {
		return horariosDeAtencion.stream().anyMatch(disp -> disp.disponibleEnDiayHora(dia, hora));
	}

	// Este metodo no se esta utilizando
	public boolean igualNombre(String nombreServicio) {
		return this.nombre.equals(nombreServicio);
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Disponibilidad> getHorariosDeAtencion() {
		return horariosDeAtencion;
	}

	public long getIdServicioCGP() {
		return idServicioCGP;
	}
	
	public void setIdServicioCGP(long idServicioCGP) {
		this.idServicioCGP = idServicioCGP;
	}
}
