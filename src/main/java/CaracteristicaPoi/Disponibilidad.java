package CaracteristicaPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="Disponibilidad")
public class Disponibilidad {
	@Id
	@GeneratedValue
	private long disponibilidadID;
	//@ElementCollection
	/*@CollectionTable(name="diasAtencionServicio",
		joinColumns=@JoinColumn(name="disponibilidadId")*/
	@Transient
	private List<DayOfWeek> dias;
	@Column(name="horarioInicial")
	private LocalTime horarioInicial;
	@Column(name="horarioFinal")
	private LocalTime horarioFinal;

	public Disponibilidad(List<DayOfWeek> unosDias,LocalTime unHorarioInicial, LocalTime unHorarioFinal){
		this.horarioInicial = unHorarioInicial;
		this.horarioFinal = unHorarioFinal;
		this.dias = unosDias;

	}
	public Disponibilidad(){
		
	}
	public boolean disponibleEnDiayHora (DayOfWeek diaConsulta,LocalTime horaConsulta){
		 return (dias.contains(diaConsulta)&&(horaConsulta.isAfter(horarioInicial) && horaConsulta.isBefore(horarioFinal)));
	}
	//Getters
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

	
	

}
