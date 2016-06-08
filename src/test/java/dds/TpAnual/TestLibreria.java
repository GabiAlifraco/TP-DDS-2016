package dds.TpAnual;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import Pois.Comercio;
import TpAnual.Disponibilidad;
import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;

public class TestLibreria {

	private Region regionLibreria;
	private Domicilio domicilioLibreria;
	private Point coordenadaLibreria;
	private Comercio elEstudiante;

	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private Point coordenadaKiosco;
	private Comercio elDiarioDelPueblo;
	private Disponibilidad disponibilidadKioscoDiario;
	private List<String> diasDeAtencionKioscoDiario = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves",
			"Viernes");
	private Disponibilidad disponibilidadLibreria;
	private List<String> diasDeAtencionLibreria = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes",
			"Sabado");

	@Before
	public void initialize() {
		domicilioLibreria = new Domicilio("Bartolome Mitre", 1999, "Junin", "Ayacucho", 100, 0, 0, 0, 1111);
		regionLibreria = new Region("CABA", "Congreso", "Bs As", "Argentina");
		coordenadaLibreria = new Point(12.4541, 21.2581);
		disponibilidadLibreria = new Disponibilidad("10:00", "19:00");
		List<String> palabrasClaveLibreria = Arrays.asList("Cuadernos", "Libros", "Lapiceras");
		elEstudiante = new Comercio("El estudiante", domicilioLibreria, regionLibreria, coordenadaLibreria,
				diasDeAtencionLibreria, disponibilidadLibreria, palabrasClaveLibreria);
        
		domicilioKiosco = new Domicilio("Junin", 541, "Av.Corrientes", "Lavalle", 2000, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaKiosco = new Point(12.8741, 21.0421);
		disponibilidadKioscoDiario = new Disponibilidad("06:00", "12:00");
		List<String> palabrasClaveKioscoDiario = Arrays.asList("Revistas", "Diarios", "Crucigrama");
		elDiarioDelPueblo = new Comercio("El diario del pueblo", domicilioKiosco, regionKiosco, coordenadaKiosco,
				diasDeAtencionKioscoDiario, disponibilidadKioscoDiario, palabrasClaveKioscoDiario);

	}

	@Test
	public void estaCercaLaLibreriaDeLKioscoDiario() {
		elEstudiante.setDistancia(200);
		elDiarioDelPueblo.setDistancia(100);
		Assert.assertTrue(elEstudiante.estaCercaDe(elDiarioDelPueblo.getCoordenada()));
	}

	@Test
	public void distanciaEntreLaLibreriaYElKioscoDiario() {
		Assert.assertEquals(52.25135818766176, elEstudiante.getCoordenada().distance(coordenadaKiosco), 0);
	}

}
