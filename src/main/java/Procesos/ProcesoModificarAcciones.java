
package Procesos;


import java.time.LocalDate;

import org.joda.time.LocalDateTime;

import Notificaciones.AlmacenadorBusquedas;
import PoliticasReejecucion.Politica;
import ProcesoAgregarAcciones.Criterio;

public class ProcesoModificarAcciones extends Proceso {

	private AlmacenadorBusquedas resultados = AlmacenadorBusquedas.getInstance();
	private Criterio criterio;
	
	public ProcesoModificarAcciones(LocalDateTime cronograma, Criterio criterio, Politica politica, AlmacenadorResultados almacenador) {

		this.cronograma = cronograma;
		this.criterio = criterio;
		this.politicaFallo = politica;
		this.almacenadorResultados = almacenador;
	}
		
	public void ejecutarProceso() {
		cantidadElementosAfectados = criterio.aplicarConfiguracion(resultados.terminalesQueEjecutaronBusquedas(LocalDate.now()));		
	}

}
