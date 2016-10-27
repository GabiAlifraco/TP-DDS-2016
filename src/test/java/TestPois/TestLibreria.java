package TestPois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import Inicializacion.CreadorDeObjetos;
import Pois.Comercio;



public class TestLibreria extends CreadorDeObjetos {

	private Region regionLibreria;
	private Domicilio domicilioLibreria;
	private Punto coordenadaLibreria;
	private Comercio elEstudiante;
	private Disponibilidad disponibilidadLibreria;
	private List<Disponibilidad> horariosLibreria = new ArrayList<Disponibilidad>();
	private List<DayOfWeek> diasLibreria = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.SATURDAY);
	private Ubicacion ubicacionLibreria;
	


	@Before
	public void initialize() {
		domicilioLibreria = new Domicilio("Bartolome Mitre", 1999, "Junin", "Ayacucho", 100, 0, 0, 0, 1111);
		regionLibreria = new Region("CABA", "Congreso", "Bs As", "Argentina");
		coordenadaLibreria = new Punto(12.4541, 21.2581);
		disponibilidadLibreria = new Disponibilidad(diasLibreria, LocalTime.of(10, 0), LocalTime.of(19, 00));
		horariosLibreria.add(disponibilidadLibreria);
		List<String> palabrasClaveLibreria = Arrays.asList("Cuadernos", "Libros", "Lapiceras");
		ubicacionLibreria = new Ubicacion(domicilioLibreria, regionLibreria, coordenadaLibreria);
		elEstudiante = new Comercio("El estudiante", ubicacionLibreria,horariosLibreria, palabrasClaveLibreria);
		this.crearKioscoPepe();
		

	}

	@Test
	public void estaCercaLaLibreriaDeLKioscoDiario() {
		elEstudiante.setDistancia(200);
		KioscoPepe.setDistancia(100);
		Assert.assertTrue(elEstudiante.estaCercaDe(KioscoPepe.getCoordenada()));
	}

	@Test
	public void distanciaEntreLaLibreriaYElKioscoDiario() {
		
		Assert.assertEquals(52.25135818766176, elEstudiante.getUbicacion().getCoordenadas()
				.distance(KioscoPepe.getCoordenada()), 0);
	}

}
