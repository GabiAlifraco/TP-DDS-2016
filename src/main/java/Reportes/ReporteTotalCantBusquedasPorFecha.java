package Reportes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Resultado.Resultado;
import Terminal.Terminal;

public class ReporteTotalCantBusquedasPorFecha extends Reporte {

	Map<LocalDate, Integer> busquedasPorFecha = new HashMap<LocalDate, Integer>();

	@Override
	public Map<LocalDate, Integer> obtenerReporte(List<Terminal> terminales) {
		terminales.stream().forEach(terminal -> completarHashMap(terminal));
		return busquedasPorFecha;	
	}

	private void completarHashMap(Terminal terminal) {
		terminal.getBusquedas().stream().forEach(resultado -> totalizarBusqueda(resultado));
	}

	private void totalizarBusqueda(Resultado resultado) {

		if (busquedasPorFecha.containsKey(resultado.getFecha())) {
			this.busquedasPorFecha.replace(resultado.getFecha(),
					busquedasPorFecha.get(resultado.getFecha()) + resultado.getCantidadResultados());
		} else {
			busquedasPorFecha.put(resultado.getFecha(), resultado.getCantidadResultados());
		}
	}
	
}
