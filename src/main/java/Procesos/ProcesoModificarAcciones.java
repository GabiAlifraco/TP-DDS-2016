
package Procesos;


import java.time.LocalDate;

import org.joda.time.LocalDateTime;

import PoliticasReejecucion.Politica;
import ProcesoAgregarAcciones.Criterio;
import Reportes.ResultadosReportes;

public class ProcesoModificarAcciones extends Proceso {

	private ResultadosReportes resultados = new ResultadosReportes();
	private Criterio criterio;
	
	public ProcesoModificarAcciones(LocalDateTime cronograma, Criterio criterio, Politica politica, AlmacenadorResultados almacenador) {

		this.cronograma = cronograma;
		this.criterio = criterio;
		this.politicaFallo = politica;
		this.almacenadorResultados = almacenador;
	}
		
	public void ejecutarProceso() {
		criterio.aplicarConfiguracion(resultados.terminalesQueEjecutaronBusquedas(LocalDate.now()));		
	}

}
