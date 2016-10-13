package TestPersistencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.ParadaColectivo;
import Pois.Poi;
import Resultado.Resultado;
import Terminal.Terminal;

public class PersistenciaResultadoBusqueda extends AbstractPersistenceTest implements WithGlobalEntityManager{
	Mapa mapa = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(mapa);
	Point coordenadas;
	Terminal terminal;
	Resultado resultado;
	LocalDate fecha;
	String fraseBuscada;
	int totalResultados;
	LocalTime horaInicio;
	LocalTime horaFin;
	Domicilio domicilioParada;
	Region regionParada;
	Point coordenadaParada;
	Ubicacion ubicacionParada;
	List<String> palabrasClave114;
	ParadaColectivo parada114;

	

	@Before
	public void initialize() {

	domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
	regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
	coordenadaParada = new Point(34.4353, 25.4632);
	ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
	palabrasClave114 = Arrays.asList("Colectivo", "Parada");
	parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);
	List<Poi> poisEncontrados = Arrays.asList(parada114);
		
	terminal = new Terminal("Terminal Abasto", servicios);
	coordenadas = new Point(-34.6030, -58.4107);
	terminal.setCoordenadaDispositivoMovil(coordenadas);
	terminal.setComunaTerminal("3");
	fecha = LocalDate.parse("2016-09-10");
	horaInicio = LocalTime.parse("11:59");
	horaFin = LocalTime.parse("12:03");
	fraseBuscada = "Parada 132";
	totalResultados = poisEncontrados.size();
	resultado = new Resultado(fecha, horaInicio, horaFin, fraseBuscada, terminal, poisEncontrados);
	entityManager().persist(resultado);
	}
	
	@Test 
	public void testPersistenciaResultado(){
		Assert.assertEquals(entityManager().find(Resultado.class, resultado.getIdResultado()), resultado);
	}
	
	@Test
	public void testPersistenciaFecha(){
		Assert.assertEquals(entityManager().find(Resultado.class, resultado.getIdResultado()).getFecha(),fecha); 
	}
	@Test
	public void testPersistenciaHoraInicioYFin(){
		Assert.assertEquals(entityManager().find(Resultado.class, resultado.getIdResultado())
				.getSegundosBusqueda(), ChronoUnit.SECONDS.between(horaFin, horaInicio));
	}
	@Test
	public void testPersistenciaFraseBuscada(){
		Assert.assertEquals(entityManager().find(Resultado.class, resultado.getIdResultado())
				.getFraseBuscada(), fraseBuscada);
	}
	@Test
	public void testPersistenciaTotalResultados(){
		Assert.assertEquals(entityManager().find(Resultado.class, resultado.getIdResultado())
				.getCantidadDeResultados(), totalResultados);
	}
	@Test
	public void testPersistenciaTerminal(){
		Assert.assertEquals(entityManager().find(Resultado.class, resultado.getIdResultado())
				.getTerminal(), terminal);
	}
	
}
