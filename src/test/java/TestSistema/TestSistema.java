package TestSistema;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
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
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import CaracteristicaPoi.Zona;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Banco;
import Pois.CGP;
import Pois.ParadaColectivo;
import Pois.Poi;
import Terminal.Terminal;

public class TestSistema extends AbstractPersistenceTest implements WithGlobalEntityManager   {

	private Terminal terminalAbasto;

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

	private List<ServicioCGP> serviciosCGP;
	private List<DayOfWeek> diasRentas;
	protected Disponibilidad disponibilidadRentas;
	private List<Disponibilidad> horariosRentas;
	private ServicioCGP rentas;
	private Disponibilidad disponibilidadTesoreria;
	private List<Disponibilidad> horariosTesoreria;
	private List<DayOfWeek> diasTesoreria;
	private ServicioCGP tesoreria;
	protected CGP comuna3;
	private Domicilio domicilio;
	private Region region;
	private Punto coordenadaCGP;
	private Ubicacion ubicacion;
	private List<String> palabras;

	
	@Before
	public void initialize() {
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

		comuna3= new CGP();
		comuna3.setNombre("cgp comuna 3");
		palabras = Arrays.asList("cgp","comuna 3","CABA","que salga colectivo del cgp");
		comuna3.setPalabrasClave(palabras);
		
		coordenadaCGP = new Punto(34.4124, 24.4856);
		domicilio = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		region= new Region("CABA", "Recoleta", "Bs As", "Argentina");
		ubicacion= new Ubicacion();
		ubicacion.setCoordenadas(coordenadaCGP);
		ubicacion.setDomicilio(domicilio);
		ubicacion.setRegion(region);
		comuna3.setUbicacion(ubicacion);
		
		diasTesoreria = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		disponibilidadTesoreria= new Disponibilidad();
		disponibilidadTesoreria.setHorarioFinal(LocalTime.of(15, 0));
		disponibilidadTesoreria.setHorarioInicial( LocalTime.of(9, 0));
		disponibilidadTesoreria.setDias(diasTesoreria);
		horariosTesoreria= Arrays.asList(disponibilidadTesoreria);		
		tesoreria= new ServicioCGP();
		tesoreria.setNombre("Tesoreria");
		tesoreria.setHorariosDeAtencion(horariosTesoreria);
		
		diasRentas = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		disponibilidadRentas= new Disponibilidad();
		disponibilidadRentas.setHorarioFinal(LocalTime.of(15,30));
		disponibilidadRentas.setHorarioInicial( LocalTime.of(9,30));
		disponibilidadRentas.setDias(diasRentas);
		horariosRentas= Arrays.asList(disponibilidadRentas);		
		rentas= new ServicioCGP();
		rentas.setNombre("Rentas");
		rentas.setHorariosDeAtencion(horariosRentas);
		
		serviciosCGP=new ArrayList<ServicioCGP>();
		serviciosCGP= Arrays.asList(tesoreria);
		serviciosCGP= Arrays.asList(rentas);
		
		comuna3.setServiciosCGP(serviciosCGP);
		
		Punto coordenadaCGP2 = new Punto(34.4124, 24.4852);
		Punto coordenadaCGP3 = new Punto(34.4120, 24.4851);
		List<Punto> zona = new ArrayList<Punto>();
		zona.add(coordenadaCGP);
		zona.add(coordenadaCGP2);
		zona.add(coordenadaCGP3);
		Zona zonaCGP = new Zona(zona);
		comuna3.setZona(zonaCGP);
		comuna3.setPalabrasClave(comuna3.getPalabrasClave());

		
		Mapa base = Mapa.getInstance();
		List<OrigenDeDatos> servicios = Arrays.asList(base);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalAbasto.getBase().agregarUnPoi(parada114);		
		terminalAbasto.getBase().agregarUnPoi(comuna3);
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);
		

	}


	@Test
	public void busquedaSinResultados() {
		Assert.assertTrue(terminalAbasto.busquedaDePuntos("Plaza", "Plaza").isEmpty());

	}

	@Test
	public void busquedaDeServicioCGPPorClave() {
		List<Poi> resultadoEsperado = Arrays.asList(comuna3);

		Assert.assertEquals(resultadoEsperado,terminalAbasto.busquedaDePuntos("Rentas", "Rentas"));

	}
	@Test
	public void busquedaDePoisPorClave() {
		
		List<Poi> resultadoEsperado = Arrays.asList(bancoSantander);
		Assert.assertEquals(resultadoEsperado,terminalAbasto.busquedaDePuntos("Cajero", "Cajero"));

	}
}