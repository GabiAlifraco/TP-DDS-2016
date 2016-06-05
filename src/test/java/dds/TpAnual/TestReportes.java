package dds.TpAnual;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import Observers.ObserverAlmacenarResultados;
import Observers.ObserverBusqueda;
import Pois.Banco;
import Pois.ParadaColectivo;
import TpAnual.BaseDePois;
import TpAnual.InfoFast;
import TpAnual.OrigenDeDatos;
import TpAnual.Poi;
import TpAnual.Resultado;
import TpAnual.Terminal;
import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;
import UbicacionPoi.Ubicacion;

public class TestReportes {

	private List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	private BaseDePois baseInterna = BaseDePois.getInstance();
	private InfoFast sistema;
	private LocalDate fecha;
	private List<OrigenDeDatos> servic;
	private LocalDate fecha2;
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	private Point coordenadaBanco;
	private List<String> palabrasClaveBanco = new ArrayList<String>();
	private Banco bancoSantander;
	private Banco bancoSantander2;
	List<ObserverBusqueda> observers = new ArrayList<ObserverBusqueda>();
	ObserverAlmacenarResultados observerAlmacenar = new ObserverAlmacenarResultados();
	private Domicilio domicilioParada;
	private Region regionParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private Ubicacion ubicacionParada; 

	@Before
	public void initialize() {
		sistema = new InfoFast();
		List<Terminal> terminales = new ArrayList<Terminal>();
		List<Resultado> busquedas = new ArrayList<Resultado>();
		servicios.add(baseInterna);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalFlorida = new Terminal("Terminal Florida", servicios);
		sistema.setTerminales(terminales);
		fecha = LocalDate.parse("2016-10-16");
		fecha2 = LocalDate.parse("2016-10-17");
        
		sistema.setReporteFechaActivado(true);
		sistema.setReporteParcial(true);
		sistema.setReporteTotal(true);
		
		coordenadaBanco = new Point(34.3243, 21.4484);
		coordenadaBanco = new Point(37.2, 28.2);
		palabrasClaveBanco.add("Cajero automatico");
		palabrasClaveBanco.add("Deposito");
		bancoSantander = new Banco("Banco Santander", coordenadaBanco, palabrasClaveBanco);

		domicilioParada = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353,25.4632);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);



		terminalAbasto.agregarObserver(observerAlmacenar);
		terminales.add(terminalAbasto);
		terminales.add(terminalFlorida);
		baseInterna.getPois().add(bancoSantander);
		baseInterna.getPois().add(parada114);

		}

	@Test
	public void imprimirReportesPorFecha() {

		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		System.out.println("Resultados por Fecha");
		sistema.obtenerReporteTotalBusquedasPorFecha();
		System.out.println("--------------------------------------");
	}

	@Test
	public void imprimirResultadosParciales() {

		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		sistema.obtenerResultadosParcialesPorTerminal(terminalAbasto);
		System.out.println("--------------------------------------");
	}

	@Test
	public void imprimirResultadosTotales() {
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		System.out.println("Resultados Totales");
		sistema.obtenerResultadosTotales();
	}
	
	@Test
	public void imprimirResultadosTotalesNoActivo() {
		
		sistema.setReporteTotal(false);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		System.out.println("Resultados Totales");
		sistema.obtenerResultadosTotales();
		System.out.println("--------------------------------------");
	}
}
