package TestABMPois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Banco;
import Pois.ParadaColectivo;
import Pois.Poi;
import Terminal.Terminal;

public class TestABMPois extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);
	
	private Domicilio domicilioParada;
	private Region regionParada;
	private Punto coordenadaParada;
	private Ubicacion ubicacionParada;
	private List<String> palabrasClave114;
	protected ParadaColectivo parada114;
	private List<DayOfWeek> dias114;
	private Disponibilidad horario114;
	private List<Disponibilidad> horariosParada114;

	private Punto coordenadaBanco;
	private List<String> palabrasClaveBanco;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	protected Banco bancoSantander;
	private Disponibilidad horarioBanco;
	private List<Disponibilidad> horariosBanco;
	private List<DayOfWeek> diasBanco;
	private Ubicacion ubicacionBanco;
	
	@Before
	public void initialize() {

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
	
		bancoSantander=new Banco();
		palabrasClaveBanco = Arrays.asList("Cajero automatico", "Deposito");
		bancoSantander.setPalabrasClave(palabrasClaveBanco);
		
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

		
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalFlorida = new Terminal("Terminal Florida", servicios);

	}

	@Test
	public void testAgregarPoi() {

		terminalAbasto.getBase().agregarUnPoi(parada114);

		Assert.assertTrue(terminalAbasto.getBase().getPois().contains(parada114));
	}

	@Test
	public void testBorrarPoi() {

		terminalAbasto.getBase().eliminarUnPoi(parada114);

		Assert.assertFalse(terminalAbasto.getBase().getPois().contains(parada114));
	}

	@Test
	public void testModificarPoi() {

		terminalAbasto.getBase().agregarUnPoi(parada114);
		parada114.setNombre("115");
		terminalAbasto.getBase().modificarUnPoi(parada114);

		int posicionParada = terminalAbasto.getBase().getPois().indexOf(parada114);

		Assert.assertEquals("115", terminalAbasto.getBase().getPois().get(posicionParada).getNombre());

	}

	@Test
	public void testDosSistemasAgreganPois() {
		terminalAbasto.getBase().agregarUnPoi(parada114);
		terminalFlorida.getBase().agregarUnPoi(bancoSantander);

		List<Poi> resultadoEsperado = Arrays.asList(parada114, bancoSantander);

		Assert.assertTrue(terminalAbasto.getBase().getPois().equals(resultadoEsperado));
	}

}
