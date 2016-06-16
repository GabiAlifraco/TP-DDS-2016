package CaracteristicaPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class Disponibilidad {
	private List<DayOfWeek> dias;
	private LocalTime horarioInicial;
	private LocalTime horarioFinal;

	public Disponibilidad (List<DayOfWeek> unosDias,LocalTime unHorarioInicial, LocalTime unHorarioFinal){
		this.horarioInicial = unHorarioInicial;
		this.horarioFinal = unHorarioFinal;
		this.dias = unosDias;

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

	
	

}
