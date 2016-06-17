package Reportes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Terminal.Terminal;

public class ReporteCantResultadosPorBusquedaYTerminal extends Reporte{

	Map<String,Integer> busquedaPorTerminal = new HashMap<String,Integer>();
	@Override
	public Map<LocalDate, Integer> obtenerReporte(List<Terminal> terminales) {
		
		return null;
	}

	@Override
	public Map<String, Integer> obtenerReportePorTerminal(List<Terminal> terminales) {
		terminales.stream().forEach(terminal -> obtenerCantidadResultadosTotales(terminal));
		return busquedaPorTerminal;
		
	}

	private void obtenerCantidadResultadosTotales(Terminal terminal) {
		if(busquedaPorTerminal.containsKey(terminal.getNombreTerminal())){
			this.busquedaPorTerminal.replace(terminal.getNombreTerminal(),
					busquedaPorTerminal.get(terminal.getNombreTerminal()) + cantidadTotalBusquedas(terminal));
		} else {
			busquedaPorTerminal.put(terminal.getNombreTerminal(),cantidadTotalBusquedas(terminal));
			
		}
	}

	private Integer cantidadTotalBusquedas(Terminal terminal) {
		
		return terminal.obtenerResultadosParciales().stream().mapToInt(i -> i).sum();
	}

	
		 
}

