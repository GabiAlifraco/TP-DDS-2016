package Notificaciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Resultado.Resultado;
import Terminal.Terminal;

public class AlmacenadorBusquedas implements NotificacionBusqueda {

	private static AlmacenadorBusquedas instance = null;
	private Map<Terminal, List<Resultado>> resultadosEncontrados = new HashMap<Terminal, List<Resultado>>();
	public List<Terminal> terminalesActivadas = new ArrayList<Terminal>();
    private Map<String,Integer> reportePorTerminal = new HashMap<String,Integer>();
    
	protected AlmacenadorBusquedas() {
	}

	public static AlmacenadorBusquedas getInstance() {
		if (instance == null) {
			instance = new AlmacenadorBusquedas();
		}
		return instance;
	}

	@Override
	public void actualizar(Resultado resultado, Terminal terminal) {
		if (resultadosEncontrados.containsKey(terminal)) {
			resultadosEncontrados.get(terminal).add(resultado);
		} else {
			terminalesActivadas.add(terminal);

			List<Resultado> resultados = new ArrayList<Resultado>();
			resultados.add(resultado);

			resultadosEncontrados.put(terminal, resultados);

		}
	}

	public Map<LocalDate, Integer> getReportePorFecha(Terminal terminal) {
		if (terminalesActivadas.contains(terminal)) {
			Map<LocalDate, Integer> reportePorFecha = new HashMap<LocalDate, Integer>();

			for (Resultado resultado : resultadosEncontrados.get(terminal)) {
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
	
	public Map<String,Integer> getReportePorTerminal(List<Terminal> terminalesActivadas){
		terminalesActivadas.stream().forEach(terminal->completarHashMap(terminal));
		return reportePorTerminal;
	}

	
	private Map<String,Integer> completarHashMap(Terminal terminal) {
		if(terminalesActivadas.contains(terminal)){
			
			for(Resultado resultado : resultadosEncontrados.get(terminal)){
				if(reportePorTerminal.containsKey(terminal.getNombreTerminal())){
					this.reportePorTerminal.replace(terminal.getNombreTerminal(),
					reportePorTerminal.get(terminal.getNombreTerminal()) + cantidadTotalBusquedas(terminal));
				} else {
					reportePorTerminal.put(terminal.getNombreTerminal(),cantidadTotalBusquedas(terminal));
					
				}
				
			}
			return reportePorTerminal;
		}else{
			return null;
		}
	}
	


	private Integer cantidadTotalBusquedas(Terminal terminal) {
		return obtenerResultadosParciales(terminal).stream().mapToInt(i -> i).sum();
	}
	
	public List<Integer> obtenerResultadosParciales(Terminal terminal) {
		List<Integer> resultadosParciales = terminal.getBusquedas().stream().map(resultado -> resultado.getCantidadResultados())
				.collect(Collectors.toList());
		return resultadosParciales;
	}

	public void activarReportes(Terminal terminal) {
		if(!(terminalesActivadas.contains(terminal))){
			terminalesActivadas.add(terminal);
		}
	}

	public void desactivarReportes(Terminal terminal) {
		if(terminalesActivadas.contains(terminal)){
			terminalesActivadas.remove(terminal);
		}
		
	}

}
