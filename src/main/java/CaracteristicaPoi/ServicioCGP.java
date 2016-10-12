package CaracteristicaPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "ServiciosCGP")
public class ServicioCGP {
	@Id
	@GeneratedValue
	private long idServicioCGP;
	
	@Column(name = "nombre")
	private String nombre;
	// @OneToMany(cascade= CascadeType.ALL)
	@OneToMany
	@OrderColumn(name="horarios")
	private List<Disponibilidad> horariosDeAtencion;

	public ServicioCGP(String unNombre, List<Disponibilidad> horarios) {
		nombre = unNombre;
		horariosDeAtencion = horarios;
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
	
	public void setHorariosDeAtencion(List<Disponibilidad> horariosDeAtencion) {
		this.horariosDeAtencion = horariosDeAtencion;
	}
}
