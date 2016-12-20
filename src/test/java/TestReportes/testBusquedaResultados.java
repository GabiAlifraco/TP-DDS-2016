package TestReportes;

import java.time.LocalDate;

import org.junit.*;
import org.junit.Before;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

import Notificaciones.AlmacenadorBusquedas;
import Resultado.Resultado;
import Terminal.Terminal;

public class testBusquedaResultados {
	MongoClient client;
	Morphia morphia = new Morphia();
	Datastore datastore;
	AlmacenadorBusquedas almacenador;

	Terminal terminalAbasto;
	Terminal terminalFlorida;
	Resultado resultadoAbasto;
	Resultado resultadoAbasto2;
	Resultado resultadoFlorida;

	@Before
	public void initialize() {
		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "resultadosBusqueda");
		
		almacenador = AlmacenadorBusquedas.getInstance();
		almacenador.setDatastore(datastore);

		terminalAbasto = new Terminal();
		terminalFlorida = new Terminal();
		terminalAbasto.setNombreTerminal("Terminal Abasto");
		terminalFlorida.setNombreTerminal("Terminal Florida");

		resultadoAbasto = new Resultado();
		resultadoAbasto2 = new Resultado();
		resultadoFlorida = new Resultado();

		resultadoAbasto.setCantidadDeResultados(20);
		resultadoAbasto.setFecha(org.joda.time.LocalDate.parse("2016-05-10"));
		resultadoAbasto.setTerminal(terminalAbasto);

		resultadoAbasto2.setCantidadDeResultados(5);
		resultadoAbasto2.setFecha(org.joda.time.LocalDate.parse("2016-09-10"));
		resultadoAbasto2.setTerminal(terminalAbasto);

		resultadoFlorida.setCantidadDeResultados(5);
		resultadoFlorida.setFecha(org.joda.time.LocalDate.parse("2016-10-10"));
		resultadoFlorida.setTerminal(terminalFlorida);
		
		datastore.save(resultadoAbasto);
		datastore.save(resultadoAbasto2);
		datastore.save(resultadoFlorida);

	}

@After
public void cleanDB(){
	client.dropDatabase("resultadosBusqueda");}

@Test
public void testFiltroFecha(){
	Query<Resultado> filtroFecha = datastore.createQuery(Resultado.class);
	filtroFecha.and(filtroFecha.criteria("fecha").greaterThan(LocalDate.parse("2016-09-01")),
			filtroFecha.criteria("fecha").lessThan((LocalDate.parse("2016-10-01"))));
	
	Assert.assertEquals(1, almacenador.buscarSegunFiltro(filtroFecha).size());
}

@Test
public void testFiltroCantidadResultados(){
	Query<Resultado> filtroCantidadResultados = datastore.createQuery(Resultado.class);
	filtroCantidadResultados.criteria("cantidadDeResultados").equal(5);

	Assert.assertEquals(2, almacenador.buscarSegunFiltro(filtroCantidadResultados).size());
}

@Test
public void testTerminal(){
	Query<Resultado> filtroTerminal = datastore.createQuery(Resultado.class);
	filtroTerminal.criteria("terminal").equal(terminalAbasto);
	
	Assert.assertEquals(2, almacenador.buscarSegunFiltro(filtroTerminal).size());
}
}