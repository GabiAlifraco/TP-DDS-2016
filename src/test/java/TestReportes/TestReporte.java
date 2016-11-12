package TestReportes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import MocksServicios.MockNotificadorAdministrador;
import Notificaciones.AlmacenadorBusquedas;
import Notificaciones.NotificacionBusqueda;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Banco;
import Pois.ParadaColectivo;
import ProcesoAgregarAcciones.ActivarNotificacion;
import ResultadosReportes.ResultadosReportes;
import Terminal.Terminal;

public class TestReporte extends AbstractPersistenceTest implements WithGlobalEntityManager   {

	private List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	private Mapa baseInterna = Mapa.getInstance();
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	

	MongoClient client;
	Morphia morphia = new Morphia();
	Datastore datastore;

	List<NotificacionBusqueda> observers = new ArrayList<NotificacionBusqueda>();
	
	private LocalDate fecha;

	private Punto coordenadaBanco;
	private List<String> palabrasClaveBanco;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	protected Banco bancoSantander;
	private Disponibilidad horarioBanco;
	private List<Disponibilidad> horariosBanco;
	private List<DayOfWeek> diasBanco;
	private Ubicacion ubicacionBanco;
	
	private Domicilio domicilioParada;
	private Region regionParada;
	private Punto coordenadaParada;
	private Ubicacion ubicacionParada;
	private List<String> palabrasClave114;
	protected ParadaColectivo parada114;
	private List<DayOfWeek> dias114;
	private Disponibilidad horario114;
	private List<Disponibilidad> horariosParada114;
	private ResultadosReportes resultadosReportes;
	
	@Before
	public void initialize() {

		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "resultadosBusqueda");
		
		bancoSantander=new Banco();
		palabrasClaveBanco = Arrays.asList("Cajero automatico", "Deposito");
		bancoSantander.setPalabrasClave(palabrasClaveBanco);
		bancoSantander.setNombre("Santander");
		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Punto(34.3243,21.4484);
		ubicacionBanco = new Ubicacion(domicilioBanco, regionBanco, coordenadaBanco);
		bancoSantander.setUbicacion(ubicacionBanco);
		
		diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		horarioBanco =new Disponibilidad();
		horarioBanco.setDias(diasBanco);
		horarioBanco.setHorarioInicial(LocalTime.of(9,30));
		horarioBanco.setHorarioFinal(LocalTime.of(15, 0));
		horariosBanco= Arrays.asList(horarioBanco);
		
		bancoSantander.setHorariosDeAtencion(horariosBanco);
		
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Punto(34.4353, 25.4632);
		ubicacionParada = new Ubicacion();
		ubicacionParada.setCoordenadas(coordenadaParada);
		ubicacionParada.setDomicilio(domicilioParada);
		ubicacionParada.setRegion(regionParada);
		palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo();
		parada114.setUbicacion(ubicacionParada);
		parada114.setPalabrasClave(palabrasClave114);
		parada114.setNombre("114");
		dias114 = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		horario114 =new Disponibilidad();
		horario114.setDias(dias114);
		horario114.setHorarioInicial(LocalTime.of(00,00));
		horario114.setHorarioFinal(LocalTime.of(23,59));
		horariosParada114=Arrays.asList(horario114);
		parada114.setHorariosDeAtencion(horariosParada114);

		servicios.add(baseInterna);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalFlorida = new Terminal("Terminal Florida", servicios);
		terminalAbasto.getBase().agregarUnPoi(parada114);
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);

		resultadosReportes = new ResultadosReportes();
	}
	@Test
	public void testObtenerReportesPorFecha() {
		
        
		Map<LocalDate, Integer> resultadoEsperado = new HashMap<LocalDate, Integer>();
		fecha = LocalDate.now();
		resultadoEsperado.put(fecha, 1);
		AlmacenadorBusquedas almacenador = AlmacenadorBusquedas.getInstance();
		terminalAbasto.agregarObserver(almacenador);
		almacenador.setDatastore(datastore);
		
	    terminalAbasto.busquedaDePuntos("Santander", "Cajero");
		
		Assert.assertEquals(resultadoEsperado, resultadosReportes.getReportePorFecha(terminalAbasto));
		
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

		Assert.assertEquals(resultadosReportes.getReportePorTerminal().size(), 2);
		Assert.assertEquals(resultadosReportes.getReportePorTerminal().get(terminalAbasto), resultadoAbasto);
		Assert.assertEquals(resultadosReportes.getReportePorTerminal().get(terminalFlorida), resultadoFlorida);
		
		client.dropDatabase("resultadosBusqueda");
	}
}
