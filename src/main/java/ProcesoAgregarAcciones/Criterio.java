package ProcesoAgregarAcciones;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Terminal.Terminal;

public abstract class Criterio {

	private Accion accion;

	protected abstract boolean cumpleCriterio(Terminal terminal);
	
	public void aplicarConfiguracion(List<Terminal> terminales){
		List<Terminal> terminalesQueCumplenCriterio = terminales.stream()
				.filter(terminal -> cumpleCriterio(terminal)).collect(Collectors.toList());
		
		terminalesQueCumplenCriterio.stream()
		.forEach(terminal -> accion.modificarConfiguracion(terminal));
		
	}
	
	
}
