package dds.TpAnual;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import TpAnual.InfoFast;
import TpAnual.OrigenDeDatos;
import TpAnual.Resultado;
import TpAnual.Terminal;

public class TestReportes {

	private List<OrigenDeDatos> servicios;
	private InfoFast sistema;
    private LocalDate fecha;
	private List<OrigenDeDatos> servic;
	private LocalDate fecha2;
	
	@Before
	public void initialize(){
	sistema = new InfoFast();
	List<Terminal> terminales = new ArrayList<Terminal>();
	List<Resultado> busquedas = new ArrayList<Resultado>();
	Terminal terminalAbasto = new Terminal("Terminal Abasto", servicios);
	Terminal terminalFlorida = new Terminal("Terminal pinpanpun",servic);
	sistema.setTerminales(terminales);
	fecha = LocalDate.parse("2016-10-16");
	fecha2 = LocalDate.parse("2016-10-17");
	Resultado resultado = new Resultado(fecha,10,"sarasa",4);
	Resultado resultado2 = new Resultado(fecha,10,"sarasa",8);
	Resultado resultado3 = new Resultado(fecha2,10,"sarasa",8);
	Resultado resultado4 = new Resultado(fecha2,10,"sarasa",15);
	terminales.add(terminalAbasto);
	terminales.add(terminalFlorida);
	terminalAbasto.getBusquedas().add(resultado);
	terminalAbasto.getBusquedas().add(resultado2);
	terminalAbasto.getBusquedas().add(resultado3);
	terminalFlorida.getBusquedas().add(resultado4);
	
	}
	
	@Test
	public void imprimirReportes(){
	  sistema.obtenerReporteTotalBusquedasPorFecha();
	}
	
	
}
