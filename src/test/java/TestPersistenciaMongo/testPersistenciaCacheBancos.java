package TestPersistenciaMongo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import Pois.Banco;
import Pois.Poi;
import cacheServicios.CacheBancos;
import cacheServicios.RespuestaServicio;

public class testPersistenciaCacheBancos {
	MongoClient client;
	Morphia morphia;
	Datastore datastore;
	RespuestaServicio respuesta;

	private Punto coordenadaBanco;
	private List<String> palabrasClaveBanco;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Ubicacion ubicacion;
	protected Banco bancoSantander;
	private Disponibilidad horarioBanco;
	private List<Disponibilidad> horariosBanco = new ArrayList<Disponibilidad>();
	private List<DayOfWeek> diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
	private LocalDate fechaRespuesta;
	private List<Poi> bancosEncontrados = new ArrayList<Poi>();
	private CacheBancos cache;

	@Before
	public void initialize() {
		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "respuestasConsulta");

		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Punto(34.3243, 21.4484);
		ubicacion = new Ubicacion(domicilioBanco, regionBanco, coordenadaBanco);
		palabrasClaveBanco = Arrays.asList("Cajero automatico", "Deposito");
		bancoSantander = new Banco("Banco Santander", palabrasClaveBanco, ubicacion);

		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);
		diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		horarioBanco = new Disponibilidad(diasBanco, LocalTime.of(9, 30), LocalTime.of(15, 00));
		horariosBanco.add(horarioBanco);
		bancoSantander.setHorariosDeAtencion(horariosBanco);

		bancosEncontrados.add(bancoSantander);

		fechaRespuesta = LocalDate.parse("2016-10-20");
		respuesta = new RespuestaServicio(fechaRespuesta, bancosEncontrados, "Deposito", "Banco Santander");

		
	}

	@Test
	public void testGuardarEnCache() {
		cache = new CacheBancos(datastore);

		Object id = cache.guardar(bancosEncontrados, "Banco Santander", "Deposito");
		
		RespuestaServicio respuesta = datastore.get(RespuestaServicio.class, id);
		
		Assert.assertEquals("Banco Santander", respuesta.getNombreBancoConsulta());
		client.dropDatabase("respuestasConsulta");
	}

	@Test
	public void testBuscarEnCache(){

		cache = new CacheBancos(datastore);

		cache.guardar(bancosEncontrados, "Banco Santander", "Deposito");
		
		List<Poi> bancos = cache.buscar("Banco Santander", "Deposito");

		Assert.assertTrue(bancos.size() == 1);
		client.dropDatabase("respuestasConsulta");
	}

	@Test
	public void testLimpiarCache(){

		cache = new CacheBancos(datastore);

		Object id = cache.getDatastore().save(respuesta).getId();
		Object idNuevaBusqueda = cache.guardar(bancosEncontrados, "Banco Santander", "Deposito");
		cache.limpiarCache();
		
		RespuestaServicio respuesta = datastore.get(RespuestaServicio.class, id);
		RespuestaServicio respuestaNueva = datastore.get(RespuestaServicio.class, idNuevaBusqueda);
		
		Assert.assertNull(respuesta);
		Assert.assertNotNull(respuestaNueva);
		
		client.dropDatabase("respuestasConsulta");
		
	}
}
