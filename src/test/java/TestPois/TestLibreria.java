package TestPois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import Pois.Comercio;
import TpAnual.Disponibilidad;


public class TestLibreria {

	private Region regionLibreria;
	private Domicilio domicilioLibreria;
	private Point coordenadaLibreria;
	private Comercio elEstudiante;
	private Disponibilidad disponibilidadLibreria;
	private List<Disponibilidad> horariosLibreria = new ArrayList<Disponibilidad>();
	private List<DayOfWeek> diasLibreria = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.SATURDAY);
	private Ubicacion ubicacionLibreria;
	
	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private Point coordenadaKiosco;
	private Comercio elDiarioDelPueblo;
	private List<Disponibilidad> horariosDiario = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadDiario;
	private List<DayOfWeek> diasDiario = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.SATURDAY);
	private Ubicacion ubicacionKiosco;

	@Before
	public void initialize() {
		domicilioLibreria = new Domicilio("Bartolome Mitre", 1999, "Junin", "Ayacucho", 100, 0, 0, 0, 1111);
		regionLibreria = new Region("CABA", "Congreso", "Bs As", "Argentina");
		coordenadaLibreria = new Point(12.4541, 21.2581);
		disponibilidadLibreria = new Disponibilidad(diasLibreria, LocalTime.of(10, 0), LocalTime.of(19, 00));
		horariosLibreria.add(disponibilidadLibreria);
		List<String> palabrasClaveLibreria = Arrays.asList("Cuadernos", "Libros", "Lapiceras");
		ubicacionLibreria = new Ubicacion(domicilioLibreria, regionLibreria, coordenadaLibreria);
		elEstudiante = new Comercio("El estudiante", ubicacionLibreria,horariosLibreria, palabrasClaveLibreria);
        
		domicilioKiosco = new Domicilio("Junin", 541, "Av.Corrientes", "Lavalle", 2000, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaKiosco = new Point(12.8741, 21.0421);
		disponibilidadDiario = new Disponibilidad (diasDiario,LocalTime.of(6,0),LocalTime.of(12, 0));
		horariosDiario.add(disponibilidadDiario);
		List<String> palabrasClaveKioscoDiario = Arrays.asList("Revistas", "Diarios", "Crucigrama");
		ubicacionKiosco = new Ubicacion(domicilioKiosco, regionKiosco, coordenadaKiosco);
		elDiarioDelPueblo = new Comercio("El diario del pueblo", ubicacionKiosco,horariosDiario, palabrasClaveKioscoDiario);

	}

	@Test
	public void estaCercaLaLibreriaDeLKioscoDiario() {
		elEstudiante.setDistancia(200);
		elDiarioDelPueblo.setDistancia(100);
		Assert.assertTrue(elEstudiante.estaCercaDe(elDiarioDelPueblo.getCoordenada()));
	}

	@Test
	public void distanciaEntreLaLibreriaYElKioscoDiario() {
		Assert.assertEquals(52.25135818766176, elEstudiante.getUbicacion().getCoordenadas()
				.distance(elDiarioDelPueblo.getCoordenada()), 0);
	}

}
