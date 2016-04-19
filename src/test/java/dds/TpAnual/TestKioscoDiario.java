package dds.TpAnual;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TpAnual.KioscoDiario;
import TpAnual.Poi;

public class TestKioscoDiario {
	
    private Poi elKioscoDeBraulio;
	private KioscoDiario unKioscoDiario;
	private Poi pepe;

	@Before
	public void initialize(){
		elKioscoDeBraulio = new Poi("Av.Callao",1070,"Santa Fe","M.T.de Alvear",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
				           "Argentina",32.43,54.12);
		pepe = new Poi("Av.Callao",1520,"Santa Fe","M.T.de Alvear",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
		               "Argentina",32.43,54.12);
		unKioscoDiario = new KioscoDiario(elKioscoDeBraulio);
		
	}
	
	@Test
	public void pepeEstaCercaDeLKioscoDeBraulio(){
		Assert.assertEquals(false,unKioscoDiario.poiCercanoAOtro(pepe));
	}
	
	@Test
	public void distanciaEntrePepeYElKioscoDeBraulio(){
		Assert.assertEquals(450, elKioscoDeBraulio.calculoDistanciaEntrePois(pepe));
	}
}


