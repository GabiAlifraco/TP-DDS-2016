package Inicializacion;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import Pois.Banco;
import Pois.CGP;
import Pois.Comercio;
import Pois.ParadaColectivo;

public abstract class CreadorDeObjetos {

	// Creación de Paradas
	private Domicilio domicilioParada;
	private Region regionParada;
	private Point coordenadaParada;
	private Ubicacion ubicacionParada;
	private List<String> palabrasClave114;
	protected ParadaColectivo parada114;

	protected void crearParada114() {
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353, 25.4632);
		ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);
	}

	// Creación de Bancos
	private Point coordenadaBanco;
	private List<String> palabrasClaveBanco;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	protected Banco bancoSantander;
	private Disponibilidad horarioBanco;
	private List<Disponibilidad> horariosBanco = new ArrayList<Disponibilidad>();
	private List<DayOfWeek> diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);

	protected void crearBancoSantander() {
		
		coordenadaBanco = new Point(34.3243,21.4484);
		palabrasClaveBanco = Arrays.asList("Cajero automatico", "Deposito");
		bancoSantander = new Banco("Banco Santander", coordenadaBanco, palabrasClaveBanco);
		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");

		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);
		diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		horarioBanco = new Disponibilidad(diasBanco, LocalTime.of(9, 30), LocalTime.of(15, 00));
		horariosBanco.add(horarioBanco);
		bancoSantander.setHorariosDeAtencion(horariosBanco);

	}

	// Creación de Comunas
	private List<ServicioCGP> serviciosCGP;
	private List<DayOfWeek> diasRentas;
	protected Disponibilidad disponibilidadCGPRentas;
	private List<Disponibilidad> horariosRentas;
	private ServicioCGP rentas;
	private Disponibilidad disponibilidadTesoreria;
	private List<Disponibilidad> horariosTesoreria;
	private List<DayOfWeek> diasTesoreria;
	private ServicioCGP tesoreria;
	protected CGP comuna3;

	protected void crearCGPComuna3() {
		serviciosCGP = new ArrayList<ServicioCGP>();

		diasRentas = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		disponibilidadCGPRentas = new Disponibilidad(diasRentas, LocalTime.of(9, 30), LocalTime.of(15, 30));
		horariosRentas = Arrays.asList(disponibilidadCGPRentas);

		diasTesoreria = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		disponibilidadTesoreria = new Disponibilidad(diasTesoreria, LocalTime.of(9, 0), LocalTime.of(15, 0));
		horariosTesoreria = Arrays.asList(disponibilidadTesoreria);

		tesoreria = new ServicioCGP("Tesoreria", horariosTesoreria);
		rentas = new ServicioCGP("Rentas", horariosRentas);
		serviciosCGP = Arrays.asList(rentas, tesoreria);

		comuna3 = new CGP("CGP comuna3", "Recoleta", serviciosCGP);
		comuna3.setCoordenada(new Point(34.4124, 24.4856));
		Point coordenadaCGP = new Point(34.4124, 24.4856);
		Point coordenadaCGP2 = new Point(34.4124, 24.4852);
		Point coordenadaCGP3 = new Point(34.4120, 24.4851);
		Polygon zonaCGP = new Polygon();
		zonaCGP.add(coordenadaCGP);
		zonaCGP.add(coordenadaCGP2);
		zonaCGP.add(coordenadaCGP3);
		comuna3.setZona(new Polygon());

	}
	
	// Creación de Carrousel
	private Domicilio domicilioCarrousel;
	private Region regionCarrousel;
	private Point coordenadaCarrousel;
	private List<Disponibilidad> horariosCarrousel = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadCarrousel;
	private Disponibilidad disponibilidadCarrousel2;
	private List<DayOfWeek> diasCarrousel = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
	private Ubicacion ubicacionCarrousel;
	protected Comercio carrouselPlinPlin;

	protected void crearCarrouselPlinPlin() {
		domicilioCarrousel = new Domicilio("Av. Estado de Israel", 4560, "LambarÃ©", "Guardia Vieja", 1200, 0, 0, 0,
				1414);
		regionCarrousel = new Region("CABA", "Almagro", "Bs.As", "Argentina");
		coordenadaCarrousel = new Point(34.599434, 58.4296912);
		disponibilidadCarrousel = new Disponibilidad(diasCarrousel, LocalTime.of(10, 0), LocalTime.of(13, 0));
		disponibilidadCarrousel2 = new Disponibilidad(diasCarrousel, LocalTime.of(17, 0), LocalTime.of(20, 30));
		horariosCarrousel.add(disponibilidadCarrousel);
		horariosCarrousel.add(disponibilidadCarrousel2);
		List<String> palabrasClaveCarrousel = Arrays.asList("Sortija", "Plaza", "Juegos");
		ubicacionCarrousel = new Ubicacion(domicilioCarrousel, regionCarrousel, coordenadaCarrousel);
		carrouselPlinPlin = new Comercio("Carrousel PlinPlin", ubicacionCarrousel, horariosCarrousel,
				palabrasClaveCarrousel);
	}
	
}