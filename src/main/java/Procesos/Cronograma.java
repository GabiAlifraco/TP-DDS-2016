package Procesos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cronograma {
	
	private List<DayOfWeek> diasEjecucion = new ArrayList<DayOfWeek>();
	private LocalTime horaEjecucion;
	
	public List<DayOfWeek> getDiasEjecucion() {
		return diasEjecucion;
	}
	public void setDiasEjecucion(List<DayOfWeek> diasEjecucion) {
		this.diasEjecucion = diasEjecucion;
	}
	public LocalTime getHoraEjecucion() {
		return horaEjecucion;
	}
	public void setHoraEjecucion(LocalTime horaEjecucion) {
		this.horaEjecucion = horaEjecucion;
	}
	

}
