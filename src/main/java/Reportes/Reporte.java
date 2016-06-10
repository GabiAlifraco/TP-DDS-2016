package Reportes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import Terminal.Terminal;

public abstract class Reporte {
	
	public abstract Map<LocalDate, Integer> obtenerReporte(List<Terminal> terminales);

}
