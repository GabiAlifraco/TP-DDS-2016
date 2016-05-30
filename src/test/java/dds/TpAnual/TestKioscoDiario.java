package dds.TpAnual;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import Pois.KioscoDiario;
import Pois.ParadaColectivo;
import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
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
	private Disponibilidad disponibilidadKioscoDiario;
	private List<String> diasDeAtencionKioscoDiario = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");
	

	@Before
	public void initialize(){
		domicilioParada = new Domicilio("Av.Monroe",1540 , "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaParada = new Point(10.2758,16.2148);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada, "114", palabrasClave114);

		domicilioKiosco = new Domicilio("Av.Monroe",1740 , "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaKiosco = new Point(10.2478,14.2547);
		disponibilidadKioscoDiario = new Disponibilidad ("06:00","12:00");
		List<String> palabrasClaveKioscoDiario = Arrays.asList("Revistas", "Diarios", "Crucigrama");
		elDiarioDelPueblo = new KioscoDiario("El diario del pueblo", domicilioKiosco, regionKiosco, coordenadaKiosco,diasDeAtencionKioscoDiario,disponibilidadKioscoDiario, palabrasClaveKioscoDiario);
	
	    
	}
	
	@Test
	public void estaCercaElKioscoDiarioDeLaParada(){
		Assert.assertTrue(elDiarioDelPueblo.estaCercaDe(coordenadaParada));
	}
	
	@Test
	public void distanciaEntreElKioscoDiarioYLaParada(){
		Assert.assertEquals(214.48906705665945,parada114.getCoordenada().distance(coordenadaKiosco),0);
	}
	
	
}
