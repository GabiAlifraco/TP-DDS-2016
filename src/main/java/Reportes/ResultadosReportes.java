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
	private Reporte reporteFecha;
	private Reporte reporteCantidadBusquedaYTerminal;
	private static ResultadosReportes instance = null;
	List<Terminal> terminales = new ArrayList<Terminal>();
	public String mailAdministrador;

	
	public Map<LocalDate, Integer> obtenerReporteTotalBusquedasPorFecha() {
		return reporteFecha.obtenerReporte(terminales);
	}

	public void obtenerResultadosParcialesPorTerminal(Terminal terminal) {

		System.out.println("Usuario: " + terminal.getNombreTerminal());
		System.out.println("Cantidad Resultados Parciales");
		terminal.obtenerResultadosParciales().stream().forEach(resultado -> System.out.println(resultado));
	}
	
	public void obtenerResultadosTotales() {
		terminales.stream().forEach(terminal -> imprimirResultadosTotales(terminal));
	}

	private void imprimirResultadosTotales(Terminal terminal) {
		System.out.println("Usuario: " + terminal.getNombreTerminal() + " " + " Cantidad de Resultados Totales: "
				+ obtenerSumatoriaBusquedas(terminal));
	}

	private int obtenerSumatoriaBusquedas(Terminal terminal) {
		return terminal.obtenerResultadosParciales().stream().mapToInt(i -> i).sum();
	}
	
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

	public Reporte getReporteFecha() {
		return reporteFecha;
	}

	public void setReporteFecha(Reporte reporteFecha) {
		this.reporteFecha = reporteFecha;
	}
	
	public void activarReporteFecha() {
		this.reporteFecha = new ReporteTotalCantBusquedasPorFecha();
	}

	public void desactivarReporteFecha() {
		this.reporteFecha = new ReporteDesactivado();
	}
    
	public void activarReporteBusqPorTerminal(){
		this.reporteCantidadBusquedaYTerminal = new ReporteCantResultadosPorBusquedaYTerminal();
	}


}
