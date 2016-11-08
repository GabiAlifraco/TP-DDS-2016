package TestProcesos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
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

import java.nio.file.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

import Procesos.Almacenador;
import Procesos.ProcesoActualizarPalabrasClave;
import MocksProcesos.MockAlmacenadorResultados;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Comercio;
import PoliticasReejecucion.NingunaAccion;
import PoliticasReejecucion.Politica;
import Terminal.Terminal;

public class TestProcesoActualizarPalabrasClave extends AbstractPersistenceTest implements WithGlobalEntityManager  {
	private Terminal terminalAbasto;
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);
	Politica politica;
	Almacenador almacenador;
	private ProcesoActualizarPalabrasClave actualizarPalabrasClave;
	private Path archivo;
	private List<String> lineas = Arrays.asList("Carrousel PlinPlin;colegio escolar uniformes modas",
			"El diario del pueblo;Revistas Diarios Crucigrama Peliculas", "El kiosco de Pepe;Revistas Crucigrama");

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

	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private List<Disponibilidad> horariosDiario = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadDiario;
	private List<DayOfWeek> diasDiario = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
	private Ubicacion ubicacionKiosco;
	private Punto coordenadaKioscoDiario;
	protected Comercio elDiarioDelPueblo;
	
	private Domicilio domicilioKioscoPepe;
	private Region regionKioscoPepe;
	private List<Disponibilidad> horariosKioscoPepe= new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadKioscoPepe;
	private List<DayOfWeek> diasKioscoPepe = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
	private Ubicacion ubicacionKioscoPepe;
	private Punto coordenadaKioscoPepe;
	private List<String> palabrasClaveKioscoPepe;
	protected Comercio kioscoPepe;
	
	@Before
	public void initialize() throws IOException {
		archivo = Paths.get("actualizarPalabrasClave.txt");
		Files.write(archivo, lineas);
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
		
		coordenadaKioscoDiario = new Punto (34.5553, 33.5422);
		domicilioKiosco = new Domicilio("Av.Monroe", 1740, "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		disponibilidadDiario = new Disponibilidad(diasDiario, LocalTime.of(6, 0), LocalTime.of(12, 0));
		horariosDiario.add(disponibilidadDiario);
		List<String> palabrasClaveKioscoDiario = Arrays.asList("Revistas", "Diarios", "Crucigrama");
		ubicacionKiosco = new Ubicacion(domicilioKiosco, regionKiosco, coordenadaKioscoDiario);
		elDiarioDelPueblo = new Comercio("El diario del pueblo", ubicacionKiosco, horariosDiario,
				palabrasClaveKioscoDiario);
		
		coordenadaKioscoPepe = new Punto(12.8741, 21.0421);
		domicilioKioscoPepe = new Domicilio("gfd", 4554, "Ofddf", "Vfffgino", 200, 0, 0, 0, 1241);
		regionKioscoPepe = new Region("CABA", "Villa Crespo", "Bs As", "Argentina");
		disponibilidadKioscoPepe = new Disponibilidad(diasKioscoPepe, LocalTime.of(6, 0), LocalTime.of(20, 0));
		horariosKioscoPepe.add(disponibilidadKioscoPepe);
		palabrasClaveKioscoPepe= Arrays.asList("kiosco", "caramelos", "comida", "bebidas");
		ubicacionKioscoPepe = new Ubicacion(domicilioKioscoPepe, regionKioscoPepe, coordenadaKioscoPepe);
		kioscoPepe = new Comercio("El Pepe", ubicacionKioscoPepe, horariosKioscoPepe,
				palabrasClaveKioscoPepe);
		
		politica = new NingunaAccion();
		almacenador = new MockAlmacenadorResultados();
		actualizarPalabrasClave = new ProcesoActualizarPalabrasClave(archivo, politica, almacenador);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		archivo.toFile().deleteOnExit();
	}

	@Test
	public void testActualizaCarrousel() throws IOException {

		terminalAbasto.getBase().agregarUnPoi(kioscoPepe);
		terminalAbasto.getBase().agregarUnPoi(elDiarioDelPueblo);
		terminalAbasto.getBase().agregarUnPoi(carrouselPlinPlin);
		actualizarPalabrasClave.leerPalabrasAModificar(archivo);
		List<String> palabrasEsperadas = Arrays.asList("colegio", "escolar", "uniformes", "modas");
		Assert.assertTrue(base.getPois().get(2).getPalabrasClave().get(0).equals(palabrasEsperadas.get(0)));
		Assert.assertTrue(base.getPois().get(2).getPalabrasClave().get(1).equals(palabrasEsperadas.get(1)));
		Assert.assertTrue(base.getPois().get(2).getPalabrasClave().get(2).equals(palabrasEsperadas.get(2)));
		Assert.assertTrue(base.getPois().get(2).getPalabrasClave().get(3).equals(palabrasEsperadas.get(3)));
	}

	@Test
	public void testActualizaElDiarioDelPueblo() throws IOException {
		terminalAbasto.getBase().agregarUnPoi(kioscoPepe);
		terminalAbasto.getBase().agregarUnPoi(elDiarioDelPueblo);
		terminalAbasto.getBase().agregarUnPoi(carrouselPlinPlin);
		actualizarPalabrasClave.leerPalabrasAModificar(archivo);
		List<String> palabrasEsperadas = Arrays.asList("Revistas", "Diarios", "Crucigrama", "Peliculas");
		Assert.assertTrue(base.getPois().get(1).getPalabrasClave().get(0).equals(palabrasEsperadas.get(0)));
		Assert.assertTrue(base.getPois().get(1).getPalabrasClave().get(1).equals(palabrasEsperadas.get(1)));
		Assert.assertTrue(base.getPois().get(1).getPalabrasClave().get(2).equals(palabrasEsperadas.get(2)));
		Assert.assertTrue(base.getPois().get(1).getPalabrasClave().get(3).equals(palabrasEsperadas.get(3)));
	}

	@Test
	public void testNoActualizoKioscoPepe() throws IOException {

		terminalAbasto.getBase().agregarUnPoi(kioscoPepe);
		terminalAbasto.getBase().agregarUnPoi(elDiarioDelPueblo);
		terminalAbasto.getBase().agregarUnPoi(carrouselPlinPlin);
		actualizarPalabrasClave.leerPalabrasAModificar(archivo);
		List<String> palabrasNoEsperadas = Arrays.asList("Revistas", "Crucigrama");
		Assert.assertFalse(base.getPois().get(0).getPalabrasClave().equals(palabrasNoEsperadas));

	}
}
