package Reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Terminal.Terminal;

public class ResultadosReportes {
	private Reporte reporteFecha;
	private Reporte reporteCantidadBusquedaYTerminal;
	List<Terminal> terminales = new ArrayList<Terminal>();
	public String mailAdministrador;

	
	public Map<LocalDate, Integer> obtenerReporteTotalBusquedasPorFecha() {
		return reporteFecha.obtenerReporte(terminales);
	}


    public void activarReporteFecha() {
		this.reporteFecha = new ReporteTotalCantBusquedasPorFecha();
	}

	public void desactivarReporteFecha() {
		this.reporteFecha = new ReporteDesactivado();
	}
    
	public void activarReporteBusqPorTerminal(){
		this.setReporteCantidadBusquedaYTerminal(new ReporteCantResultadosPorBusquedaYTerminal());
	}
	
	public Reporte getReporteFecha() {
		return reporteFecha;
	}
    public void setReporteFecha(Reporte reporteFecha) {
		this.reporteFecha = reporteFecha;
	}
    
    public Reporte getReporteCantidadBusquedaYTerminal() {
		return reporteCantidadBusquedaYTerminal;
	}
    public void setReporteCantidadBusquedaYTerminal(Reporte reporteCantidadBusquedaYTerminal) {
		this.reporteCantidadBusquedaYTerminal = reporteCantidadBusquedaYTerminal;
	}
    
    private List<Terminal> getTerminales() {
		return this.terminales;
	}
    public void setTerminales(List<Terminal> terminales) {
		this.terminales = terminales;
	}
    
    public void agregarTerminal(Terminal nuevaTerminal) {
		this.getTerminales().add(nuevaTerminal);
	}

	

	


}
