package TpAnual;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InfoFast {

	private static InfoFast instance = null;
	List<Terminal> terminales = new ArrayList<Terminal>();
	private String mailAdministrador;
	Map<LocalDate, Integer> busquedaFecha = new HashMap<LocalDate, Integer>();

	public static InfoFast getInstance() {
		if (instance == null) {
			instance = new InfoFast();
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

	public void setMailAdministrador(String mailAdministrador) {
		this.mailAdministrador = mailAdministrador;
	}

	public void obtenerReporteTotalBusquedasPorFecha() {

		terminales.stream().forEach(terminal -> completarHashMap(terminal));
		imprimirReportePorFecha(busquedaFecha);
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

		System.out.println("Usuario: " + terminal.getNombreTerminal());
		System.out.println("Cantidad Resultados Parciales");
		terminal.obtenerResultadosParciales().stream().forEach(resultado -> System.out.println(resultado));
	}

	public void obtenerResultadosTotales() {
		terminales.stream().forEach(terminal -> imprimirResultadosTotales(terminal));

	}

	public void imprimirResultadosTotales(Terminal terminal) {
		System.out.println("Usuario: " + terminal.getNombreTerminal() + " "+ " Cantidad de Resultados Totales: "
				+ obtenerSumatoriaBusquedas(terminal));
	}

	public int obtenerSumatoriaBusquedas(Terminal terminal) {
		return terminal.obtenerResultadosParciales().stream().mapToInt(i -> i).sum();
	}

}
