package TestPois;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Inicializacion.CreadorDeObjetos;

public class TestBanco extends CreadorDeObjetos {

	@Before
	public void initialize() {
		this.crearBancoSantander();
		this.crearParada114();
	}

	@Test
	public void estaCercaElBancoDeLaParada() {
		// Assert.assertEquals(true,bancoSantander.esCerca(coordenadaParada));
		Assert.assertTrue(bancoSantander.estaCercaDe(parada114.getCoordenada()));
	}

	@Test
	public void distanciaEntreElBancoyLaParada() {
		Assert.assertEquals(368.6, bancoSantander.getCoordenada().distance(parada114.getCoordenada()), 0.1);
	}

}
