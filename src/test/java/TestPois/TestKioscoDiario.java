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

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import Inicializacion.CreadorDeObjetos;
import Pois.Comercio;
import Pois.ParadaColectivo;

public class TestKioscoDiario extends CreadorDeObjetos {

	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private Point coordenadaKiosco;
	private Comercio elDiarioDelPueblo;
	private List<Disponibilidad> horariosDiario = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadDiario;
	private List<DayOfWeek> diasDiario = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
	private Ubicacion ubicacionKiosco;

	@Before
	public void initialize() {
		this.crearParada114();

		domicilioKiosco = new Domicilio("Av.Monroe", 1740, "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaKiosco = new Point(34.5553, 33.5422);
		disponibilidadDiario = new Disponibilidad(diasDiario, LocalTime.of(6, 0), LocalTime.of(12, 0));
		horariosDiario.add(disponibilidadDiario);
		List<String> palabrasClaveKioscoDiario = Arrays.asList("Revistas", "Diarios", "Crucigrama");
		ubicacionKiosco = new Ubicacion(domicilioKiosco, regionKiosco, coordenadaKiosco);
		elDiarioDelPueblo = new Comercio("El diario del pueblo", ubicacionKiosco, horariosDiario,
				palabrasClaveKioscoDiario);
	}

	@Test
	public void estaCercaElKioscoDiarioDeLaParada() {
		elDiarioDelPueblo.setDistancia(800);
		Assert.assertTrue(elDiarioDelPueblo.estaCercaDe(parada114.getCoordenada()));
	}

	@Test
	public void distanciaEntreElKioscoDiarioYLaParada() {
		Assert.assertEquals(740, parada114.getCoordenada().distance(coordenadaKiosco), 0.5);
	}

	@Test
	public void noEstaDisponibleElDiarioUnLunesALaNoche() {
		Assert.assertFalse(
				elDiarioDelPueblo.estaDisponible("El diario del pueblo", DayOfWeek.MONDAY, LocalTime.of(23, 30)));
	}

}