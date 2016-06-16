package Reportes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import Terminal.Terminal;

public class ReporteDesactivado extends Reporte{



	@Override
	public Map<LocalDate, Integer> obtenerReporte(List<Terminal> terminales) {
		return null;
	}

	@Override
	public Map<String, Integer> obtenerReportePorTerminal(List<Terminal> terminales) {
		return null;
	}

}
