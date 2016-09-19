package Procesos;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.joda.time.LocalDateTime;

import PoliticasReejecucion.Politica;

public abstract class Proceso {

	public LocalDateTime cronograma;
	protected Lock mutex = new ReentrantLock();
	protected LocalDateTime fechaHoraEjecucion;
	protected boolean finalizoOK;
	protected int cantidadElementosAfectados;
	protected Almacenador almacenadorResultados;
	protected Politica politicaFallo;

	public void setCronograma(LocalDateTime cronogramaEjecucion) {
		this.cronograma = cronogramaEjecucion;
	}

	public Date getCronograma() {
		return cronograma.toDate();
	}

	public void ejecutar() {
		fechaHoraEjecucion = LocalDateTime.now();

		try {
			this.ejecutarProceso();
			finalizoOK = true;
		}

		catch (Exception e) {

			cantidadElementosAfectados = 0;
			finalizoOK = false;
			politicaFallo.aplicarPolitica(this);

		} finally {

			ResultadoEjecucion resultado = new ResultadoEjecucion(cantidadElementosAfectados, fechaHoraEjecucion,
					finalizoOK, this);
			almacenadorResultados.guardarResultado(resultado);
		}

	}

	protected abstract void ejecutarProceso();
}
