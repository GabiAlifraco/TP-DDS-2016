package ResultadosReportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Notificaciones.AlmacenadorBusquedas;
import Resultado.Resultado;
import Terminal.Terminal;

public class ResultadosReportes {

	private AlmacenadorBusquedas almacenador = AlmacenadorBusquedas.getInstance();
	private List<Terminal> terminales = new ArrayList<Terminal>();

	
	public Map<LocalDate, Integer> getReportePorFecha(Terminal terminal) {
		if (almacenador.getTerminalesActivadas().contains(terminal)) {
			Map<LocalDate, Integer> reportePorFecha = new HashMap<LocalDate, Integer>();

			for (Resultado resultado : almacenador.getResultadosEncontrados().get(terminal)) {
				if (reportePorFecha.containsKey(resultado.getFecha())) {
					reportePorFecha.replace(resultado.getFecha(),
							reportePorFecha.get(resultado.getFecha()) + resultado.getCantidadResultados());
				} else {
					reportePorFecha.put(resultado.getFecha(), resultado.getCantidadResultados());
				}
			}
			return reportePorFecha;

		} else {
			return null;
		}
	}

	public Map<Terminal, List<Integer>> getReportePorTerminal() {
		if (!(almacenador.getTerminalesActivadas()).isEmpty()) {
			Map<Terminal, List<Integer>> reportePorTerminal = new HashMap<Terminal, List<Integer>>();
			for (Terminal terminal: (almacenador.getTerminalesActivadas())){
			
				List<Resultado> resultadosTerminal = almacenador.getResultadosEncontrados().get(terminal);	
				List<Integer> cantidadResultados;
				cantidadResultados = resultadosTerminal.stream()
									.map(resultado -> resultado.getCantidadResultados())
									.collect(Collectors.toList());
			
				reportePorTerminal.put(terminal, cantidadResultados);
			}
			
			return reportePorTerminal;
		} else {
			return null;
		}
	}
	
	public void activarReportes(Terminal terminal) {
		if (!((almacenador.getTerminalesActivadas()).contains(terminal))) {
			almacenador.getTerminalesActivadas().add(terminal);
		}
	}

	public void desactivarReportes(Terminal terminal) {
		if ((almacenador.getTerminalesActivadas()).contains(terminal)) {
			almacenador.getTerminalesActivadas().remove(terminal);
		}
	}

	
}
