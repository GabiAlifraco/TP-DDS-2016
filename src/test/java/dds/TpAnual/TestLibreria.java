package dds.TpAnual;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TpAnual.Libreria;

import TpAnual.Poi;

public class TestLibreria {

    private Poi elEstudiante;
	private Libreria unaLibreria;
	private Poi juan;

	@Before
	public void initialize(){
		elEstudiante = new Poi("Arenales",1300,"Santa Fe","Junin",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
				           "Argentina",32.43,54.12);
		juan = new Poi("Arenales",1850,"Santa Fe","Junin",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
		               "Argentina",32.43,54.12);
		unaLibreria = new Libreria(elEstudiante);
		
	}
	
	@Test
	public void juanEstaCercaDeLaLibreria(){
		Assert.assertEquals(false,unaLibreria.poiCercanoAOtro(juan));
	}
	
	@Test
	public void distanciaEntreJuanYLaLibreria(){
		Assert.assertEquals(550, elEstudiante.calculoDistanciaEntrePois(juan));
	}
}

