package dds.TpAnual;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TpAnual.ParadaColectivo;
import TpAnual.Poi;

public class TestParada {

	    private Poi parada114;
		private ParadaColectivo unaParada;
		private Poi juan;

		@Before
		public void initialize(){
			parada114 = new Poi("Arenales",1300,"Santa Fe","Junin",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
					           "Argentina",32.43,54.12);
			juan = new Poi("Arenales",1350,"Santa Fe","Junin",2000,0,0,0,1111,"CABA","Recoleta","Buenos Aires",
			               "Argentina",32.43,54.12);
			unaParada = new ParadaColectivo(parada114);
			
		}
		
		@Test
		public void juanEstaCercaDel114(){
			Assert.assertEquals(true,unaParada.poiCercanoAOtro(juan));
		}
		
		@Test
		public void distanciaEntreJuanYLaParada114(){
			Assert.assertEquals(50, parada114.calculoDistanciaEntrePois(juan));
		}
}

