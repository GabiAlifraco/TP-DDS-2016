package dds.TpAnual;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TpAnual.CGP;
import TpAnual.Poi;

public class TestCGP {

    private Poi comuna3;
	private CGP unCGP;
	private Poi juan;

	@Before
	public void initialize(){
		comuna3 = new Poi("Arenales",1100,"Santa Fe","Junin",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
				           "Argentina",32.43,54.12);
		juan = new Poi("Arenales",1350,"Santa Fe","Junin",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
		               "Argentina",32.43,54.12);
		unCGP = new CGP(comuna3);
		
	}
	
	@Test
	public void juanEstaCercaDeLaComuna3(){
		Assert.assertEquals(true,unCGP.poiCercanoAOtro(juan));
	}
	
	@Test
	public void distanciaEntreJuanYLaParada114(){
		Assert.assertEquals(250, comuna3.calculoDistanciaEntrePois(juan));
	}
}

