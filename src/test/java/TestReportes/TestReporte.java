package TestReportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import Terminal.Terminal;

public class TestReporte extends CreadorDeObjetos {

	private List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	private Mapa baseInterna = Mapa.getInstance();
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;

	List<NotificacionBusqueda> observers = new ArrayList<NotificacionBusqueda>();

	private LocalDate fecha;

	@Before
	public void initialize() {

		this.crearBancoSantander();
		this.crearParada114();

		servicios.add(baseInterna);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalFlorida = new Terminal("Terminal Florida", servicios);

		baseInterna.getPois().clear();
		baseInterna.getPois().add(bancoSantander);
		baseInterna.getPois().add(parada114);

	}
	@Test
	public void testObtenerReportesPorFecha() {
		Map<LocalDate, Integer> resultadoEsperado = new HashMap<LocalDate, Integer>();
		fecha = LocalDate.now();
		resultadoEsperado.put(fecha, 1);
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
		AlmacenadorBusquedas almacenador = AlmacenadorBusquedas.getInstance();
		almacenador.getTerminalesActivadas().clear();
		terminalAbasto.agregarObserver(almacenador);
		terminalFlorida.agregarObserver(almacenador);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		terminalFlorida.busquedaDePuntos("Facultad", "UTN");
		List<Integer> resultadoAbasto = Arrays.asList(1);
		List<Integer> resultadoFlorida = Arrays.asList(0);

		Assert.assertEquals(almacenador.getReportePorTerminal().size(), 2);
		Assert.assertEquals(almacenador.getReportePorTerminal().get(terminalAbasto), resultadoAbasto);
		Assert.assertEquals(almacenador.getReportePorTerminal().get(terminalFlorida), resultadoFlorida);
	}
}
