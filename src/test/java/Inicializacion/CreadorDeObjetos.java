package Inicializacion;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import Mocks.MockNotificadorAdministrador;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Banco;
import Pois.CGP;
import Pois.Comercio;
import Pois.ParadaColectivo;
import Resultado.Resultado;
import Terminal.Terminal;

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
	//Kiosco Diario

	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private List<Disponibilidad> horariosDiario = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadDiario;
	private List<DayOfWeek> diasDiario = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
	private Ubicacion ubicacionKiosco;
	
	protected Comercio elDiarioDelPueblo;
	protected void crearElDiarioDelPueblo(Point coordenada){

		domicilioKiosco = new Domicilio("Av.Monroe", 1740, "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		disponibilidadDiario = new Disponibilidad(diasDiario, LocalTime.of(6, 0), LocalTime.of(12, 0));
		horariosDiario.add(disponibilidadDiario);
		List<String> palabrasClaveKioscoDiario = Arrays.asList("Revistas", "Diarios", "Crucigrama");
		ubicacionKiosco = new Ubicacion(domicilioKiosco, regionKiosco, coordenada);
		elDiarioDelPueblo = new Comercio("El diario del pueblo", ubicacionKiosco, horariosDiario,
				palabrasClaveKioscoDiario);
	}
	
	
	//Crear DTO

	private List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
	
	private ServicioDTO atencionCiudadana = new ServicioDTO();
	private RangoServicioDTO rango1AtencionCiudadana = new RangoServicioDTO();
	private RangoServicioDTO rango2AtencionCiudadana = new RangoServicioDTO();
	private List<RangoServicioDTO> rangosAtencionCiudadana = new ArrayList<RangoServicioDTO>();
	
	private ServicioDTO infracciones = new ServicioDTO();
	private RangoServicioDTO rango1Infracciones = new RangoServicioDTO();
	private List<RangoServicioDTO> rangosInfracciones = new ArrayList<RangoServicioDTO>();

	protected CentroDTO centroDTO = new CentroDTO();
	
	protected void crearCentroDTO(){
		
	centroDTO.setNumeroComuna(3);
	centroDTO.setZonasIncluidas("Balvanera, San Cristóbal");
	centroDTO.setDirector("Christian Etchezur");
	centroDTO.setDomicilioCompletoCGP("Junín 521");
	centroDTO.setTelefonoCGP("4375-0644/45");

	
	rango1AtencionCiudadana.setNumeroSemana(1);
	rango1AtencionCiudadana.setHorarioDesde(9);
	rango1AtencionCiudadana.setMinutosDesde(0);
	rango1AtencionCiudadana.setHorarioHasta(15);
	rango2AtencionCiudadana.setMinutosHasta(0);
	
	rango2AtencionCiudadana.setNumeroSemana(2);
	rango2AtencionCiudadana.setHorarioDesde(10);
	rango2AtencionCiudadana.setMinutosDesde(0);
	rango2AtencionCiudadana.setHorarioHasta(18);
	rango2AtencionCiudadana.setMinutosHasta(0);
	
	rangosAtencionCiudadana.add(rango1AtencionCiudadana);
	rangosAtencionCiudadana.add(rango2AtencionCiudadana);
	
	atencionCiudadana.setRangosDTO(rangosAtencionCiudadana);
	
	atencionCiudadana.setNombreServicio("Atención ciudadana");

	rango1Infracciones.setNumeroSemana(1);
	rango1Infracciones.setHorarioDesde(10);
	rango1Infracciones.setMinutosDesde(20);
	rango1Infracciones.setHorarioHasta(9);
	rango1Infracciones.setMinutosHasta(5);
	rangosInfracciones.add(rango1Infracciones);
	
	infracciones.setRangosDTO(rangosInfracciones);
	infracciones.setNombreServicio("Infracciones");

	serviciosDTO.add(infracciones);
	serviciosDTO.add(atencionCiudadana);
	
	centroDTO.setServiciosDTO(serviciosDTO);
	}

	//Envio de Mail
	
	private Resultado resultado;
	private LocalDate fecha;
	protected MockNotificadorAdministrador notificadorAdministrador;
	private Terminal terminal;
	private List<OrigenDeDatos> servicios;
	
	protected void crearMail(){
		
		fecha = LocalDate.parse("2016-10-16");
		notificadorAdministrador = new MockNotificadorAdministrador();
		terminal = new Terminal("Terminal Abasto", servicios);
		terminal.getObserverBusquedas().add(notificadorAdministrador);
		resultado = new Resultado(fecha, LocalTime.of(10, 40, 02), LocalTime.of(10, 40, 10), "sarasa", 4, terminal);
		notificadorAdministrador.setTiempoMaximoBusqueda(5);
		
		terminal.notificarBusqueda(resultado);
	}
}