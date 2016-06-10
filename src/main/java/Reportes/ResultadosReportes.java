package Reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Resultado.Resultado;
import Terminal.Terminal;

public class ResultadosReportes {
	private boolean reporteFechaActivado;
	private boolean reporteParcial;
	private boolean reporteTotal;
	private static ResultadosReportes instance = null;
	List<Terminal> terminales = new ArrayList<Terminal>();
	public String mailAdministrador;

	Map<LocalDate, Integer> busquedaFecha = new HashMap<LocalDate, Integer>();

	public static ResultadosReportes getInstance() {
		if (instance == null) {
			instance = new ResultadosReportes();
		}
		return instance;
	}

	

	public void agregarTerminal(Terminal nuevaTerminal) {
		this.getTerminales().add(nuevaTerminal);
	}

	private List<Terminal> getTerminales() {
		return this.terminales;
	}

	public void setTerminales(List<Terminal> terminales) {
		this.terminales = terminales;
	}

	public String getMailAdministrador() {
		return mailAdministrador;
	}

	public void obtenerReporteTotalBusquedasPorFecha() {
		if (reporteFechaActivado) {
			terminales.stream().forEach(terminal -> completarHashMap(terminal));
			imprimirReportePorFecha(busquedaFecha);
		} else {
			System.out.println("El reporte está desactivado");
		}
	}

	public void imprimirReportePorFecha(Map<LocalDate, Integer> busquedaFecha) {
		Iterator<LocalDate> it = busquedaFecha.keySet().iterator();
		while (it.hasNext()) {
			LocalDate key = it.next();
			System.out.println("Fecha: " + key.toString() + " Cantidad de Resultados: " + busquedaFecha.get(key));
		}
	}

	public void completarHashMap(Terminal terminal) {

		terminal.getBusquedas().stream().forEach(resultado -> totalizarBusqueda(resultado));
	}

	public void totalizarBusqueda(Resultado resultado) {

		if (busquedaFecha.containsKey(resultado.getFecha())) {
			this.busquedaFecha.replace(resultado.getFecha(),
					busquedaFecha.get(resultado.getFecha()) + resultado.getCantidadResultados());
		} else {
			busquedaFecha.put(resultado.getFecha(), resultado.getCantidadResultados());
		}
	}

	public void obtenerResultadosParcialesPorTerminal(Terminal terminal) {
		if (reporteParcial) {

			System.out.println("Usuario: " + terminal.getNombreTerminal());
			System.out.println("Cantidad Resultados Parciales");
			terminal.obtenerResultadosParciales().stream().forEach(resultado -> System.out.println(resultado));
		} else {
			System.out.println("El reporte está desactivado");
		}
	}

	public void obtenerResultadosTotales() {
		if (reporteTotal) {
			terminales.stream().forEach(terminal -> imprimirResultadosTotales(terminal));
		} else {
			System.out.println("El reporte está desactivado");
		}
	}

	public void imprimirResultadosTotales(Terminal terminal) {
		System.out.println("Usuario: " + terminal.getNombreTerminal() + " " + " Cantidad de Resultados Totales: "
				+ obtenerSumatoriaBusquedas(terminal));
	}

	public int obtenerSumatoriaBusquedas(Terminal terminal) {
		return terminal.obtenerResultadosParciales().stream().mapToInt(i -> i).sum();
	}

	public void setReporteFechaActivado(boolean reporteFechaActivado) {
		this.reporteFechaActivado = reporteFechaActivado;
	}

	public void setReporteParcial(boolean reporteParcial) {
		this.reporteParcial = reporteParcial;
	}

	public void setReporteTotal(boolean reporteTotal) {
		this.reporteTotal = reporteTotal;
	}
}
