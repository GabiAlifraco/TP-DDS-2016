package CaracteristicaPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.*;

import org.bson.types.ObjectId;

import converter.LocalTimeConverter;

@javax.persistence.Entity
@javax.persistence.Table(name="Disponibilidades")
@org.mongodb.morphia.annotations.Converters(LocalTimeConverter.class)
public class Disponibilidad {
	@javax.persistence.Enumerated(EnumType.STRING)
	@javax.persistence.ElementCollection
	@javax.persistence.OrderColumn()
	private List<DayOfWeek> dias;
	
	@javax.persistence.Transient
	@org.mongodb.morphia.annotations.Id
	ObjectId id;
	
	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	@org.mongodb.morphia.annotations.Transient
	private long idDisponibilidad;
	
	@javax.persistence.Column(name = "horarioInicial")
	private LocalTime horarioInicial;
	
	@javax.persistence.Column(name="horarioFinal")
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

