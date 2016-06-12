package TestPois;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Inicializacion.CreadorDeObjetos;

public class TestParada extends CreadorDeObjetos {

	@Before
	public void initialize() {
		this.crearBancoSantander();
		this.crearParada114();
	}

	@Test
	public void estaCercaElBancoDeLaParada() {
		Assert.assertFalse(parada114.estaCercaDe(bancoSantander.getCoordenada()));
	}

	@Test
	public void distanciaEntreElBancoyLaParada() {
		Assert.assertEquals(368, parada114.getCoordenada().distance(bancoSantander.getCoordenada()), 1);
	}

}
