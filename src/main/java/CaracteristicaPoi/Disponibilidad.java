package CaracteristicaPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Disponibilidad")
public class Disponibilidad {
	// @OneToMany(cascade= CascadeType.ALL)
	@Enumerated(EnumType.STRING)
	@ElementCollection
	@OrderColumn(name="Dia")
	private List<DayOfWeek> dias;

	@Id
	@GeneratedValue
	private long idDisponibilidad;
	@Column(name = "horarioInicial")
	private LocalTime horarioInicial;
	@Column(name="horarioFinal")
	private LocalTime horarioFinal;

	public Disponibilidad(List<DayOfWeek> unosDias, LocalTime unHorarioInicial, LocalTime unHorarioFinal) {
		this.horarioInicial = unHorarioInicial;
		this.horarioFinal = unHorarioFinal;
		this.dias = unosDias;

	}

	public Disponibilidad() {

	}

	public boolean disponibleEnDiayHora(DayOfWeek diaConsulta, LocalTime horaConsulta) {
		return (dias.contains(diaConsulta)
				&& (horaConsulta.isAfter(horarioInicial) && horaConsulta.isBefore(horarioFinal)));
	}

	// Getters
	public List<DayOfWeek> getDias() {
		return dias;
	}

	public LocalTime getHorarioInicial() {
		return horarioInicial;
	}

	public LocalTime getHorarioFinal() {
		return horarioFinal;
	}
	public void setHorarioInicial(LocalTime horarioInicial) {
		this.horarioInicial = horarioInicial;
	}
	public void setHorarioFinal(LocalTime horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public long getIdDisponibilidad() {
		return idDisponibilidad;
	}
	
	public void setIdDisponibilidad(long idDisponibilidad) {
		this.idDisponibilidad = idDisponibilidad;
	}
	
	public void setDias(List<DayOfWeek> dias) {
		this.dias = dias;
	}
}

