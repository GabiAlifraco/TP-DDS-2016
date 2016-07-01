package Procesos;

import java.time.LocalDate;

import ProcesoAgregarAcciones.Criterio;
import Reportes.ResultadosReportes;

public class ProcesoModificarAcciones extends Proceso {

	private ResultadosReportes resultados = new ResultadosReportes();
	private Criterio criterio;
	
	public ProcesoModificarAcciones(Cronograma cronograma, Criterio criterio) {

		this.cronograma = cronograma;
		this.criterio = criterio;
	}

	public boolean ejecutar() {
		
		criterio.aplicarConfiguracion(resultados.terminalesQueEjecutaronBusquedas(LocalDate.now()));
		
		
		return finalizoOK;
	}

}
