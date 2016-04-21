package dds.TpAnual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;


import TpAnual.Domicilio;
import TpAnual.KioscoDiario;
import TpAnual.ParadaColectivo;
import TpAnual.Region;
public class TestKioscoDiario {

	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private Point coordenadaKiosco;
	private KioscoDiario elDiarioDelPueblo;

	@Before
	public void initialize(){
		domicilioParada = new Domicilio("Av.Monroe",1540 , "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaParada = new Point(10.2758,16.2148);
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada);
		
		domicilioKiosco = new Domicilio("Av.Monroe",1740 , "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaKiosco = new Point(10.2478,14.2547);
		elDiarioDelPueblo = new KioscoDiario(domicilioKiosco, regionKiosco, coordenadaKiosco);
	}
	
	@Test
	public void estaCercaElKioscoDiarioDeLaParada(){
		Assert.assertEquals(true,elDiarioDelPueblo.poiCercanoAOtro(coordenadaParada));
	}
	
	@Test
	public void distanciaEntreElKioscoDiarioYLaParada(){
		Assert.assertEquals(214.48906705665945,parada114.getCoordenada().distance(coordenadaKiosco),0);
	}
	
	
}
