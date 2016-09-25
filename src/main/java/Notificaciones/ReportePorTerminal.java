package Notificaciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Resultado.Resultado;
import Terminal.Terminal;

public class ReportePorTerminal implements NotificacionBusqueda {

	private Map<String, Integer> busquedaPorTerminal = new HashMap<String, Integer>();

	@Override
	public void actualizar(Resultado resultado, Terminal terminal) {

		if (busquedaPorTerminal.containsKey(resultado.getTerminal().getNombreTerminal())) {
			this.busquedaPorTerminal.replace(resultado.getTerminal().getNombreTerminal(),
					busquedaPorTerminal.get(resultado.getTerminal().getNombreTerminal())
							+ cantidadTotalBusquedas(resultado, terminal));
		} else {
			busquedaPorTerminal.put(resultado.getTerminal().getNombreTerminal(),
					cantidadTotalBusquedas(resultado, terminal));

		}
	}

	private Integer cantidadTotalBusquedas(Resultado resultado, Terminal terminal) {

		return obtenerResultadosParciales(terminal).stream().mapToInt(i -> i).sum();
	}

	public List<Integer> obtenerResultadosParciales(Terminal terminal) {
		List<Integer> resultadosParciales = terminal.getBusquedas().stream()
				.map(resultado -> resultado.getCantidadResultados()).collect(Collectors.toList());
		return resultadosParciales;
	}

}
