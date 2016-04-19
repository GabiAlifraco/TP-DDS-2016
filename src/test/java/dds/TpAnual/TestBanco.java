package dds.TpAnual;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TpAnual.Banco;
import TpAnual.Poi;


public class TestBanco {

	private Poi bancoSantander;
	private Banco unBanco;
	private Poi martin;

	@Before
	public void initialize(){
		bancoSantander = new Poi("Arenales",1200,"Santa Fe","Junin",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
				           "Argentina",32.43,54.12);
		martin = new Poi("Arenales",900,"Santa Fe","Junin",2100,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
		               "Argentina",32.43,54.12);
		unBanco = new Banco(bancoSantander);
		
	}
    @Test
    public void estaMartinCercaDelBanco(){
    	Assert.assertEquals(true, unBanco.poiCercanoAOtro(martin));
    }
    @Test
    public void distanciaEntreMartinYElBanco(){
    	Assert.assertEquals(358, bancoSantander.calculoDistanciaEntrePois(martin));
    }
}
