package TestDisponibilidadPoi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import OrigenesDeDatos.BaseDePois;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Banco;
import Pois.CGP;
import Pois.Comercio;
import Pois.ParadaColectivo;


import TpAnual.Terminal;


public class TestDisponibilidadDeUnPoi {
	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private Ubicacion ubicacionParada;
	private ParadaColectivo parada114;

	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;
	private Disponibilidad horarioBanco;
	private List<Disponibilidad> horariosBanco = new ArrayList<Disponibilidad>();
	private List<DayOfWeek> diasBanco = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);


	private Domicilio domicilioCarrousel;
	private Region regionCarrousel;
	private Point coordenadaCarrousel;
	private Comercio carrouselPlinPlin;
	private List<Disponibilidad> horariosCarrousel = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadCarrousel;
	private Disponibilidad disponibilidadCarrousel2;
	private List<DayOfWeek> diasCarrousel = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.SATURDAY);
	private Ubicacion ubicacionCarrousel;
	
	private CGP comuna3;

	private Disponibilidad disponibilidadCGPRentas;
	private List<DayOfWeek> diasRentas = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);
	private List<Disponibilidad> horariosRentas = new ArrayList<Disponibilidad>();
	private ServicioCGP rentas;

	private Disponibilidad disponibilidadTesoreria;
	private ServicioCGP tesoreria;
	private List<Disponibilidad> horariosTesoreria = new ArrayList<Disponibilidad>();
	private List<DayOfWeek> diasTesoreria = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);

	private List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();

	private List<String> palabrasClaveBanco = new ArrayList<String>();
	
	BaseDePois base = BaseDePois.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);
	

	@Before
	public void initialize() {
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353, 25.4632);
		ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);

		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243, 21.4484);
		palabrasClaveBanco.add("Cajero automatico");
		palabrasClaveBanco.add("Deposito");
		horarioBanco = new Disponibilidad(diasBanco, LocalTime.of(9,30),LocalTime.of(15,00));
		horariosBanco.add(horarioBanco);
		bancoSantander = new Banco("Banco Santander", coordenadaBanco, palabrasClaveBanco);
		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);
		bancoSantander.setHorariosDeAtencion(horariosBanco);

		domicilioCarrousel = new Domicilio("Av. Estado de Israel",4560, "LambarÃ©","Guardia Vieja",1200,0,0,0,1414);
		regionCarrousel = new Region ("CABA","Almagro","Bs.As","Argentina");
		coordenadaCarrousel = new Point (34.599434, 58.4296912);
		disponibilidadCarrousel = new Disponibilidad (diasCarrousel,LocalTime.of(10,0),LocalTime.of(13,0));
		disponibilidadCarrousel2 = new Disponibilidad (diasCarrousel,LocalTime.of(17,0),LocalTime.of(20,30));
		horariosCarrousel.add(disponibilidadCarrousel);horariosCarrousel.add(disponibilidadCarrousel2);
		List<String> palabrasClaveCarrousel = Arrays.asList("Sortija", "Plaza", "Juegos");
		ubicacionCarrousel = new Ubicacion(domicilioCarrousel, regionCarrousel, coordenadaCarrousel);
		carrouselPlinPlin = new Comercio ("Carrousel PlinPlin",ubicacionCarrousel,horariosCarrousel, palabrasClaveCarrousel);
		

		disponibilidadTesoreria =  new Disponibilidad (diasTesoreria,LocalTime.of(9,0),LocalTime.of(15,0));
		horariosTesoreria.add(disponibilidadTesoreria);
		tesoreria = new ServicioCGP("Tesoreria",horariosTesoreria);

		disponibilidadCGPRentas = new Disponibilidad (diasRentas,LocalTime.of(9,30),LocalTime.of(15,30));
		horariosRentas.add(disponibilidadCGPRentas);
		rentas = new ServicioCGP("Rentas",horariosRentas);

		new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		new Point(34.4353, 24.4856);
		new Polygon();
		comuna3 = new CGP("CGP comuna3","Recoleta",serviciosCGP);
		comuna3.serviciosCGP.add(tesoreria);
		comuna3.serviciosCGP.add(rentas);
	}
	
	
	@Test
	public void estaElCarrouselDisponible() {
		Terminal terminalFlorida = new Terminal("Terminal Florida", servicios);
		terminalFlorida.getBase().getPois().clear();
		terminalFlorida.getBase().getPois().add(parada114);
		terminalFlorida.getBase().getPois().add(bancoSantander);
		terminalFlorida.getBase().getPois().add(carrouselPlinPlin);
		terminalFlorida.getBase().getPois().add(comuna3);

		Assert.assertTrue(terminalFlorida.estaDisponiblePoi("Carrousel PlinPlin",DayOfWeek.SATURDAY,"12:30"));

	}
	
	@Test
	public void estaElBancoDisponible() {
		
		Terminal terminalAbasto = new Terminal("Terminal Abasto", servicios);

		terminalAbasto.getBase().getPois().clear();
		terminalAbasto.getBase().getPois().add(parada114);
		terminalAbasto.getBase().getPois().add(bancoSantander);
		terminalAbasto.getBase().getPois().add(carrouselPlinPlin);
		Assert.assertFalse(terminalAbasto.estaDisponiblePoi("Banco Santander",DayOfWeek.TUESDAY, "20:00"));
	}

	@Test
	public void estaElColectivoDisponible() {
		Terminal terminalCampus = new Terminal("Terminal Campus", servicios);
		terminalCampus.getBase().getPois().clear();
		terminalCampus.getBase().getPois().add(parada114);
		terminalCampus.getBase().getPois().add(bancoSantander);
		terminalCampus.getBase().getPois().add(carrouselPlinPlin);
		Assert.assertTrue(terminalCampus.estaDisponiblePoi("114",DayOfWeek.SATURDAY, "12:30"));
	}

	@Test
	public void estaTesoreriaEstaDisponible() {
		Terminal terminalTeatroColon = new Terminal("Terminal Teatro Colon", servicios);
		terminalTeatroColon.getBase().getPois().clear();
		terminalTeatroColon.getBase().getPois().add(parada114);
		terminalTeatroColon.getBase().getPois().add(bancoSantander);
		terminalTeatroColon.getBase().getPois().add(carrouselPlinPlin);
		terminalTeatroColon.getBase().getPois().add(comuna3);
		Assert.assertFalse(terminalTeatroColon.estaDisponiblePoi("Tesoreria",DayOfWeek.SUNDAY, "11:30"));
	}
	
}