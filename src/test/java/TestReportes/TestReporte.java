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
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

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
	

	MongoClient client;
	Morphia morphia = new Morphia();
	Datastore datastore;

	List<NotificacionBusqueda> observers = new ArrayList<NotificacionBusqueda>();

	private LocalDate fecha;

	@Before
	public void initialize() {

		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "resultadosBusqueda");
		
		
		this.crearBancoSantander();
		this.crearParada114();

		servicios.add(baseInterna);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalFlorida = new Terminal("Terminal Florida", servicios);
		terminalAbasto.getBase().getPois().clear();
		if(!terminalAbasto.getBase().entityManager().isOpen()){
		terminalAbasto.getBase().entityManager().getTransaction().begin();
		terminalAbasto.getBase().agregarUnPoi(parada114);
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);
		terminalAbasto.getBase().entityManager().getTransaction().commit();}
		terminalAbasto.getBase().agregarUnPoi(parada114);
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);

		
	}
	@Test
	public void testObtenerReportesPorFecha() {
		

		Map<LocalDate, Integer> resultadoEsperado = new HashMap<LocalDate, Integer>();
		fecha = LocalDate.now();
		resultadoEsperado.put(fecha, 1);
		AlmacenadorBusquedas almacenador = AlmacenadorBusquedas.getInstance();
		almacenador.setDatastore(datastore);
		terminalAbasto.agregarObserver(almacenador);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		Assert.assertEquals(resultadoEsperado, almacenador.getReportePorFecha(terminalAbasto));
		
		client.dropDatabase("resultadosBusqueda");

	}

	@Test 
	public void modificarConfiguracion(){
		MockNotificadorAdministrador mockNotificacion = new MockNotificadorAdministrador();
		mockNotificacion.setTiempoMaximoBusqueda(200);
		ActivarNotificacion activar = new ActivarNotificacion(mockNotificacion);
		activar.modificarConfiguracion(terminalAbasto);
		terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		
		Assert.assertTrue(terminalAbasto.getNotificadoresBusqueda().contains(mockNotificacion));
	}
	
	@Test
	public void obtenerReportePorTerminal(){
		AlmacenadorBusquedas almacenador = AlmacenadorBusquedas.getInstance();
		almacenador.setDatastore(datastore);
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
		
		client.dropDatabase("resultadosBusqueda");
	}
}
