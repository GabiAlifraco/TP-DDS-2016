package TestReportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Inicializacion.CreadorDeObjetos;
import MocksServicios.MockNotificadorAdministrador;
import Notificaciones.AlmacenadorBusquedas;
import Notificaciones.NotificacionBusqueda;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import ProcesoAgregarAcciones.ActivarNotificacion;
import Procesos.Almacenador;
import Reportes.Reporte;
import Reportes.ReporteCantResultadosPorBusquedaYTerminal;
import Reportes.ReporteTotalCantBusquedasPorFecha;
import Reportes.ResultadosReportes;
import Resultado.Resultado;
import Terminal.Terminal;

public class TestReporte extends CreadorDeObjetos {

	private List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	private Mapa baseInterna = Mapa.getInstance();
	private ResultadosReportes sistema;
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;

	List<NotificacionBusqueda> observers = new ArrayList<NotificacionBusqueda>();

	private List<Terminal> terminales;
	private Reporte reporteCantResPorBusqYTerm;
	private ReporteTotalCantBusquedasPorFecha reporteFecha;
	private LocalDate fecha3;

	@Before
	public void initialize() {

		this.crearBancoSantander();
		this.crearParada114();

		sistema = new ResultadosReportes();
		terminales = new ArrayList<Terminal>();
		servicios.add(baseInterna);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalFlorida = new Terminal("Terminal Florida", servicios);
		sistema.setTerminales(terminales);

		baseInterna.getPois().clear();
		terminales.add(terminalAbasto);
		terminales.add(terminalFlorida);
		baseInterna.getPois().add(bancoSantander);
		baseInterna.getPois().add(parada114);

		sistema.activarReporteFecha();
		sistema.activarReporteBusqPorTerminal();
		reporteCantResPorBusqYTerm = new ReporteCantResultadosPorBusquedaYTerminal();
		reporteFecha = new ReporteTotalCantBusquedasPorFecha();
	}

	@Test
	public void imprimirReportesPorFecha() {
		Map<LocalDate, Integer> resultadoEsperado = new HashMap<LocalDate, Integer>();
		fecha3 = LocalDate.now();
		resultadoEsperado.put(fecha3, 1);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");

		Assert.assertEquals(resultadoEsperado, reporteFecha.obtenerReporte(terminales));

	}

	/*@Test
	public void imprimirResultadosTotales() {
		Map<String, Integer> resultadoEsperado = new HashMap<String, Integer>();
		resultadoEsperado.put("Terminal Abasto", 1);
		resultadoEsperado.put("Terminal Florida", 0);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");

		Assert.assertEquals(resultadoEsperado, reporteCantResPorBusqYTerm.obtenerReportePorTerminal(terminales));

	}*/

	@Test
	public void testAlmacenadorBusquedas() {
		Map<LocalDate, Integer> resultadoEsperado = new HashMap<LocalDate, Integer>();
		fecha3 = LocalDate.now();
		resultadoEsperado.put(fecha3, 1);
		AlmacenadorBusquedas almacenador = AlmacenadorBusquedas.getInstance();
		terminalAbasto.agregarObserver(almacenador);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		Assert.assertEquals(resultadoEsperado, almacenador.getReportePorFecha(terminalAbasto));

	}

	@Test 
	public void modificarConfiguracion(){
		MockNotificadorAdministrador mockNotificacion = new MockNotificadorAdministrador();
		mockNotificacion.setTiempoMaximoBusqueda(200);
		ActivarNotificacion activar = new ActivarNotificacion(mockNotificacion);
		activar.modificarConfiguracion(terminalAbasto);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		
		Assert.assertTrue(terminalAbasto.getObserverBusquedas().contains(mockNotificacion));
	}
	
	@Test
	public void obtenerReportePorTerminal(){
		Map<String,Integer> resultadoEsperado = new HashMap<String,Integer>();
		resultadoEsperado.put("Terminal Abasto",1);
		resultadoEsperado.put("Terminal Florida", 0);
		AlmacenadorBusquedas almacenador = AlmacenadorBusquedas.getInstance();
		terminalAbasto.agregarObserver(almacenador);
		terminalFlorida.agregarObserver(almacenador);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		
		Assert.assertEquals(resultadoEsperado,almacenador.getReportePorTerminal(terminales));
	}
}
