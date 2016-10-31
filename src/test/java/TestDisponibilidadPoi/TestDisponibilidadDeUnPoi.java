package TestDisponibilidadPoi;

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
import Pois.Comercio;
import Pois.ParadaColectivo;
import Terminal.Terminal;

public class TestDisponibilidadDeUnPoi extends AbstractPersistenceTest implements WithGlobalEntityManager  {
	Mapa base;
	private Terminal terminalTeatroColon;
	private Terminal terminalCampus;
	private Terminal terminalAbasto ;
	private Terminal terminalFlorida;
	private List<OrigenDeDatos>  servicios;
	
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

	private Domicilio domicilioCarrousel;
	private Region regionCarrousel;
	private Punto coordenadaCarrousel;
	private List<Disponibilidad> horariosCarrousel = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadCarrousel;
	private Disponibilidad disponibilidadCarrousel2;
	private List<DayOfWeek> diasCarrousel = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
	private List<DayOfWeek> diasCarrouse2 = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
	private Ubicacion ubicacionCarrousel;
	protected Comercio carrouselPlinPlin;
	
	@Before
	public void initialize() {
		palabrasClaveBanco = Arrays.asList("Cajero automatico", "Deposito");
		
		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Punto(34.3243,21.4484);
		ubicacionBanco = new Ubicacion(domicilioBanco, regionBanco, coordenadaBanco);
		
		bancoSantander = new Banco("Banco Santander", palabrasClaveBanco, ubicacionBanco);
		
		diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		horarioBanco = new Disponibilidad(diasBanco,LocalTime.of(9,30), LocalTime.of(15, 00));
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
		comuna3.setNombre("CGP Comuna 3");
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

		domicilioCarrousel = new Domicilio("Av. Estado de Israel", 4560, "LambarÃ©", "Guardia Vieja", 1200, 0, 0, 0,
				1414);
		regionCarrousel = new Region("CABA", "Almagro", "Bs.As", "Argentina");
		coordenadaCarrousel = new Punto(34.599434, 58.4296912);
		ubicacionCarrousel = new Ubicacion();
		ubicacionCarrousel.setCoordenadas(coordenadaCarrousel);
		ubicacionCarrousel.setDomicilio(domicilioCarrousel);
		ubicacionCarrousel.setRegion(regionCarrousel);
		
		disponibilidadCarrousel = new Disponibilidad();
		disponibilidadCarrousel.setDias(diasCarrousel);
		disponibilidadCarrousel.setHorarioInicial(LocalTime.of(10, 0));
		disponibilidadCarrousel.setHorarioFinal( LocalTime.of(13, 0));
		
		disponibilidadCarrousel2 = new Disponibilidad();
		disponibilidadCarrousel2.setDias(diasCarrouse2);
		disponibilidadCarrousel2.setHorarioFinal(LocalTime.of(20, 30));
		disponibilidadCarrousel2.setHorarioInicial(LocalTime.of(17, 0));
		horariosCarrousel.add(disponibilidadCarrousel);
		horariosCarrousel.add(disponibilidadCarrousel2);
		List<String> palabrasClaveCarrousel = Arrays.asList("Sortija", "Plaza", "Juegos");
		
		carrouselPlinPlin = new Comercio();
		carrouselPlinPlin.setUbicacion(ubicacionCarrousel);
		carrouselPlinPlin.setHorariosDeAtencion(horariosCarrousel);
		carrouselPlinPlin.setPalabrasClave(palabrasClaveCarrousel);
		carrouselPlinPlin.setNombre("Carrousel PlinPlin");
	    
		base = Mapa.getInstance();
		base.agregarUnPoi(parada114);
		base.agregarUnPoi(bancoSantander);
		base.agregarUnPoi(carrouselPlinPlin);
		base.agregarUnPoi(comuna3);
		
		servicios = Arrays.asList(base);
		}

	@Test
	public void estaElCarrouselDisponible() {
		terminalFlorida = new Terminal("Terminal Florida", servicios);

		Assert.assertTrue(terminalFlorida.estaDisponiblePoi("Carrousel PlinPlin", DayOfWeek.SATURDAY, "12:30"));

	}

	@Test
	public void estaElBancoDisponible() {

		terminalAbasto = new Terminal("Terminal Abasto", servicios);

		Assert.assertFalse(terminalAbasto.estaDisponiblePoi("Banco Santander", DayOfWeek.TUESDAY, "20:00"));
	}

	@Test
	public void estaElColectivoDisponible() {
		terminalCampus = new Terminal("Terminal Campus", servicios);
		Assert.assertTrue(terminalCampus.estaDisponiblePoi("114", DayOfWeek.SATURDAY, "12:30"));
	}

	@Test
	public void estaTesoreriaEstaDisponible() {
		terminalTeatroColon = new Terminal("Terminal Teatro Colon", servicios);

		Assert.assertFalse(terminalTeatroColon.estaDisponiblePoi("Tesoreria", DayOfWeek.SUNDAY, "11:30"));
	}

}