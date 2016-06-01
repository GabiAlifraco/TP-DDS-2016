package TpAnual;


import java.time.LocalTime;

public class Disponibilidad {
	private LocalTime horarioInicial;
	private LocalTime horarioFinal;

	public Disponibilidad (String unHorarioInicial, String unHorarioFinal){
		this.horarioInicial = LocalTime.parse(unHorarioInicial);
		this.horarioFinal = LocalTime.parse(unHorarioFinal);

	}

	public LocalTime getHorarioInicial() {
		return horarioInicial;
	}

	public LocalTime getHorarioFinal() {
		return horarioFinal;
	}

}
