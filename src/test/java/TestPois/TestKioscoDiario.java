package TestPois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Inicializacion.CreadorDeObjetos;

public class TestKioscoDiario extends CreadorDeObjetos {

	@Before
	public void initialize() {
		this.crearParada114();
		this.crearElDiarioDelPueblo();
	}

	@Test
	public void estaCercaElKioscoDiarioDeLaParada() {
		elDiarioDelPueblo.setDistancia(800);
		Assert.assertTrue(elDiarioDelPueblo.estaCercaDe(parada114.getCoordenada()));
	}

	@Test
	public void distanciaEntreElKioscoDiarioYLaParada() {
		Assert.assertEquals(740, parada114.getCoordenada().distance(elDiarioDelPueblo.getCoordenada()), 0.5);
	}

	@Test
	public void noEstaDisponibleElDiarioUnLunesALaNoche() {
		Assert.assertFalse(
				elDiarioDelPueblo.estaDisponible("El diario del pueblo", DayOfWeek.MONDAY, LocalTime.of(23, 30)));
	}

}