package Procesos;

import org.joda.time.LocalDateTime;

public class ResultadoEjecucion {

	int cantidadElementosAfectados;
	LocalDateTime fechaHoraEjecucion;
	boolean finalizoOK;
	Proceso proceso; 
	
	public ResultadoEjecucion(int cantidadElementosAfectados, LocalDateTime fechaHoraEjecucion, boolean finalizoOK, Proceso proceso) {
		this.cantidadElementosAfectados = cantidadElementosAfectados;
		this.fechaHoraEjecucion = fechaHoraEjecucion;
		this.finalizoOK = finalizoOK;
		this.proceso = proceso;
	}

	public int getCantidadElementosAfectados() {
		return cantidadElementosAfectados;
	}
	
	public LocalDateTime getFechaHoraEjecucion() {
		return fechaHoraEjecucion;
	}
	
	public boolean finalizoOK() {
		return finalizoOK;
	}
	
	public Proceso getProceso() {
		return proceso;
	}
}
