package Notificaciones;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Resultado.Resultado;
import Terminal.Terminal;

public class ReporteCantBusquedasPorFecha implements NotificacionBusqueda{

	private Map<LocalDate, Integer> busquedasPorFecha = new HashMap<LocalDate, Integer>();
	
	@Override
	public void actualizar(Resultado resultado,Terminal terminal) {
		resultado.getTerminal().getBusquedas().stream().forEach(res->totalizarResultado(resultado));
		
	}

	
	private void totalizarResultado(Resultado resultado) {
		if (busquedasPorFecha.containsKey(resultado.getFecha())) {
			this.busquedasPorFecha.replace(resultado.getFecha(),
					busquedasPorFecha.get(resultado.getFecha()) + resultado.getCantidadResultados());
		} else {
			busquedasPorFecha.put(resultado.getFecha(), resultado.getCantidadResultados());
		}
	}


	
}


