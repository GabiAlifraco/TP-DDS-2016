package Inicializacion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import CaracteristicaPoi.Zona;
import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import MocksServicios.MockNotificadorAdministrador;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Banco;
import Pois.CGP;
import Pois.Comercio;
import Pois.ParadaColectivo;
import Pois.Poi;
import Resultado.Resultado;
import Terminal.Terminal;

public abstract class CreadorDeObjetos {

	// Creación de Paradas
	private Domicilio domicilioParada;
	private Region regionParada;
	private Punto coordenadaParada;
	private Ubicacion ubicacionParada;
	private List<String> palabrasClave114;
	protected ParadaColectivo parada114;
	private List<DayOfWeek> dias114;
	private Disponibilidad horario114;
	private List<Disponibilidad> horariosParada114;

	protected void crearParada114() {
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
		
	}

	// Creación de Bancos
	private Punto coordenadaBanco;
	private List<String> palabrasClaveBanco;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	protected Banco bancoSantander;
	private Disponibilidad horarioBanco;
	private List<Disponibilidad> horariosBanco;
	private List<DayOfWeek> diasBanco;

	protected void crearBancoSantander() {
		
		bancoSantander=new Banco();
		palabrasClaveBanco = Arrays.asList("Cajero automatico", "Deposito");
		bancoSantander.setPalabrasClave(palabrasClaveBanco);
		
		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Punto(34.3243,21.4484);
		ubicacion = new Ubicacion();
		ubicacion.setDomicilio(domicilioBanco);
		ubicacion.setRegion(regionBanco);
		ubicacion.setCoordenadas(coordenadaBanco);
		bancoSantander.setUbicacion(ubicacion);
		
		diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		horarioBanco =new Disponibilidad();
		horarioBanco.setDias(diasBanco);
		horarioBanco.setHorarioInicial(LocalTime.of(9,30));
		horarioBanco.setHorarioFinal(LocalTime.of(15, 0));
		horariosBanco= Arrays.asList(horarioBanco);
		
		bancoSantander.setHorariosDeAtencion(horariosBanco);

	}

	// Creación de Comunas
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

	protected void crearCGPComuna3() {
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

	}
	
	// Creación de Carrousel
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

	protected void crearCarrouselPlinPlin() {
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
	}
	//Kiosco Diario

	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private List<Disponibilidad> horariosDiario = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadDiario;
	private List<DayOfWeek> diasDiario = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
	private Ubicacion ubicacionKiosco;
	private Punto coordenadaKioscoDiario;
	
	protected Comercio elDiarioDelPueblo;
	protected void crearElDiarioDelPueblo(){
		coordenadaKioscoDiario = new Punto (34.5553, 33.5422);
		domicilioKiosco = new Domicilio("Av.Monroe", 1740, "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		disponibilidadDiario = new Disponibilidad(diasDiario, LocalTime.of(6, 0), LocalTime.of(12, 0));
		horariosDiario.add(disponibilidadDiario);
		List<String> palabrasClaveKioscoDiario = Arrays.asList("Revistas", "Diarios", "Crucigrama");
		ubicacionKiosco = new Ubicacion(domicilioKiosco, regionKiosco, coordenadaKioscoDiario);
		elDiarioDelPueblo = new Comercio("El diario del pueblo", ubicacionKiosco, horariosDiario,
				palabrasClaveKioscoDiario);
	}
	protected Comercio KioscoPepe;
	protected void crearKioscoPepe(){

		coordenadaKioscoDiario = new Punto(12.8741, 21.0421);
		domicilioKiosco = new Domicilio("gfd", 4554, "Ofddf", "Vfffgino", 200, 0, 0, 0, 1241);
		regionKiosco = new Region("CABA", "Villa Crespo", "Bs As", "Argentina");
		disponibilidadDiario = new Disponibilidad(diasDiario, LocalTime.of(6, 0), LocalTime.of(20, 0));
		horariosDiario.add(disponibilidadDiario);
		List<String> palabrasClaveKioscoDiario = Arrays.asList("kiosco", "caramelos", "comida", "bebidas");
		ubicacionKiosco = new Ubicacion(domicilioKiosco, regionKiosco, coordenadaKioscoDiario);
		KioscoPepe = new Comercio("El Pepe", ubicacionKiosco, horariosDiario,
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
	centroDTO.setDomicilioCompletoCGP("Calle Junin 521");
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
		terminal.agregarObserver(notificadorAdministrador);
		notificadorAdministrador.setTiempoMaximoBusqueda(5);
		List<Poi> poisEncontrados = Arrays.asList(parada114);
		resultado = new Resultado(fecha, LocalTime.of(10, 40, 02), LocalTime.of(10, 40, 10), "sarasa", terminal, poisEncontrados);
		
		terminal.notificarBusqueda(resultado,terminal);
	}
	
}