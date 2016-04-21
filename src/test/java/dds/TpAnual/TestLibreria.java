package dds.TpAnual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;


import TpAnual.Domicilio;
import TpAnual.KioscoDiario;
import TpAnual.Libreria;
import TpAnual.Region;
public class TestLibreria {

	private Region regionLibreria;
	private Domicilio domicilioLibreria;
	private Point coordenadaLibreria;
	private Libreria elEstudiante;
	
	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private Point coordenadaKiosco;
	private KioscoDiario elDiarioDelPueblo;

	@Before
	public void initialize(){
		domicilioLibreria = new Domicilio("Bartolome Mitre",1999 , "Junin", "Ayacucho", 100, 0, 0, 0, 1111);
		regionLibreria = new Region("CABA", "Congreso", "Bs As", "Argentina");
		coordenadaLibreria = new Point(12.4541,21.2581);
		elEstudiante = new Libreria(domicilioLibreria, regionLibreria, coordenadaLibreria);
		
		domicilioKiosco = new Domicilio("Junin",541 , "Av.Corrientes", "Lavalle", 2000, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaKiosco = new Point(12.8741,21.0421);
		elDiarioDelPueblo = new KioscoDiario(domicilioKiosco, regionKiosco, coordenadaKiosco);
	}
	
	@Test
	public void estaCercaLaLibreriaDeLKioscoDiario(){
		Assert.assertEquals(false,elDiarioDelPueblo.poiCercanoAOtro(coordenadaLibreria));
	}
	
	@Test
	public void distanciaEntreLaLibreriaYElKioscoDiario(){
		Assert.assertEquals(511.4121073196172,elEstudiante.getCoordenada().distance(coordenadaKiosco),0);
	}
	
	
}
