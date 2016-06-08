package dds.TpAnual;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;
import Pois.Comercio;
import Pois.ParadaColectivo;
import TpAnual.Disponibilidad;
import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;
import UbicacionPoi.Ubicacion;

public class TestKioscoDiario {

	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private Ubicacion ubicacionParada;
	
	private Domicilio domicilioKiosco;
	private Region regionKiosco;
	private Point coordenadaKiosco;
	private Comercio elDiarioDelPueblo;
	private List<Disponibilidad> horariosDiario = new ArrayList<Disponibilidad>();
	private Disponibilidad disponibilidadDiario;
	private List<DayOfWeek> diasDiario = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.SATURDAY);
	

	@Before
	public void initialize(){
		domicilioParada = new Domicilio("Av.Monroe",1540 , "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaParada = new Point(10.2758,16.2148);
		ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);
		
		domicilioKiosco = new Domicilio("Av.Monroe",1740 , "Olazabal", "Virrey del Pino", 1900, 0, 0, 0, 1111);
		regionKiosco = new Region("CABA", "Palermo", "Bs As", "Argentina");
		coordenadaKiosco = new Point(10.2478,14.2547);
		disponibilidadDiario = new Disponibilidad (diasDiario,LocalTime.of(6,0),LocalTime.of(12, 0));
		horariosDiario.add(disponibilidadDiario);
		List<String> palabrasClaveKioscoDiario = Arrays.asList("Revistas", "Diarios", "Crucigrama");
		elDiarioDelPueblo = new Comercio("El diario del pueblo", domicilioKiosco, regionKiosco, coordenadaKiosco,horariosDiario, palabrasClaveKioscoDiario);
				                         
	
	    
	}
	
	@Test
	public void estaCercaElKioscoDiarioDeLaParada(){
		elDiarioDelPueblo.setDistancia(300);
		Assert.assertTrue(elDiarioDelPueblo.estaCercaDe(coordenadaParada));
	}
	
	@Test
	public void distanciaEntreElKioscoDiarioYLaParada(){
		Assert.assertEquals(214.48906705665945,parada114.getCoordenada().distance(coordenadaKiosco),0);
	}
	@Test
	public void noEstaDisponibleElDiarioUnLunesALaNoche(){
		Assert.assertFalse(elDiarioDelPueblo.estaDisponible("El diario del pueblo", DayOfWeek.MONDAY,LocalTime.of(23, 30)));
	}
	
}
