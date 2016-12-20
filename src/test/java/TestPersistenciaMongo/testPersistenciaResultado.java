package TestPersistenciaMongo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.ParadaColectivo;
import Pois.Poi;
import Resultado.Resultado;
import Terminal.Terminal;
import converter.LocalTimeConverter;

public class testPersistenciaResultado {

	MongoClient client;
	Morphia morphia = new Morphia();
	Datastore datastore;

	Mapa mapa = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(mapa);
	Punto coordenadas;
	Terminal terminal;
	Resultado resultado;
	LocalDate fecha;
	String fraseBuscada;
	int totalResultados;
	LocalTime horaInicio;
	LocalTime horaFin;
	Domicilio domicilioParada;
	Region regionParada;
	Punto coordenadaParada;
	Ubicacion ubicacionParada;
	List<String> palabrasClave114;
	ParadaColectivo parada114;

	@Before
	public void initialize() {

		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "resultados");
		morphia.getMapper().getConverters().addConverter(LocalTimeConverter.class);

		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Punto(34.4353, 25.4632);
		ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);
		List<Poi> poisEncontrados = Arrays.asList(parada114);

		terminal = new Terminal("Terminal Abasto", servicios);
		coordenadas = new Punto(-34.6030, -58.4107);
		terminal.setCoordenadaDispositivoMovil(coordenadas);
		terminal.setComunaTerminal("3");
		fecha = LocalDate.parse("2016-09-10");
		horaInicio = LocalTime.of(11, 59);
		horaFin = LocalTime.of(12, 03);
		fraseBuscada = "Parada 132";
		totalResultados = poisEncontrados.size();
		resultado = new Resultado(fecha, fraseBuscada, terminal, poisEncontrados);
	}
	@After
	public void dropDB() {

		client.dropDatabase("resultados");
	}

	@Test
	public void testPersistenciaFecha() {
		Object id = datastore.save(resultado).getId();
		Resultado resultadoMongo = datastore.get(Resultado.class, id);
		
		Assert.assertEquals(resultadoMongo.getFecha(), fecha);

	}


	@Test
	public void testPersistenciaFraseBuscada() {
		Object id = datastore.save(resultado).getId();
		Resultado resultadoMongo = datastore.get(Resultado.class, id);
		
		Assert.assertEquals(resultadoMongo.getFraseBuscada(), fraseBuscada);

	}

	@Test
	public void testPersistenciaTotalResultados() {
		Object id = datastore.save(resultado).getId();
		Resultado resultadoMongo = datastore.get(Resultado.class, id);
		
		Assert.assertEquals(resultadoMongo.getCantidadDeResultados(), totalResultados);
	}

	@Test
	public void testPersistenciaTerminal() {
		Object id = datastore.save(resultado).getId();
		Resultado resultadoMongo = datastore.get(Resultado.class, id);
		
		Assert.assertEquals(resultadoMongo.getTerminal().getNombreTerminal(), terminal.getNombreTerminal());
	}
}
