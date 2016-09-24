package ProcesoAgregarAcciones;

import java.util.List;
import java.util.stream.Collectors;

import Terminal.Terminal;

public abstract class Criterio {

	private Accion accion;
	int cantidadTerminalesAfectadas = 0;
	
	protected abstract boolean cumpleCriterio(Terminal terminal);

	public int aplicarConfiguracion(List<Terminal> terminales) {
		List<Terminal> terminalesQueCumplenCriterio = terminales.stream().filter(terminal -> cumpleCriterio(terminal))
				.collect(Collectors.toList());

		for (Terminal terminal : terminalesQueCumplenCriterio) {
			accion.modificarConfiguracion(terminal);
			cantidadTerminalesAfectadas++;
		}
		return cantidadTerminalesAfectadas;
	}

}
