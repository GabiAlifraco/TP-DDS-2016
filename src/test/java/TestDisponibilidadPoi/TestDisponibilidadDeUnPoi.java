package TestDisponibilidadPoi;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Inicializacion.CreadorDeObjetos;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Terminal.Terminal;

public class TestDisponibilidadDeUnPoi extends CreadorDeObjetos {
	
	private Terminal terminalTeatroColon;
	private Terminal terminalCampus;
	private Terminal terminalAbasto ;
	private Terminal terminalFlorida;
	private List<OrigenDeDatos>  servicios;
	

	@Before
	public void initialize() {
		this.crearBancoSantander();
		this.crearParada114();
		this.crearCGPComuna3();
		this.crearCarrouselPlinPlin();
		
	    
		Mapa base = Mapa.getInstance();
		base.getPois().clear();
		base.entityManager().getTransaction().begin();
		base.agregarUnPoi(parada114);
		base.agregarUnPoi(bancoSantander);
		base.agregarUnPoi(carrouselPlinPlin);
		base.agregarUnPoi(comuna3);
		base.entityManager().getTransaction().commit();
		
		servicios = Arrays.asList(base);
		}

	@Test
	public void estaElCarrouselDisponible() {
		terminalFlorida = new Terminal("Terminal Florida", servicios);

		Assert.assertTrue(terminalFlorida.estaDisponiblePoi("Carrousel PlinPlin", DayOfWeek.SATURDAY, "12:30"));

	}

	@Test
	public void estaElBancoDisponible() {

		terminalAbasto = new Terminal("Terminal Abasto", servicios);

		Assert.assertFalse(terminalAbasto.estaDisponiblePoi("Banco Santander", DayOfWeek.TUESDAY, "20:00"));
	}

	@Test
	public void estaElColectivoDisponible() {
		terminalCampus = new Terminal("Terminal Campus", servicios);

		Assert.assertTrue(terminalCampus.estaDisponiblePoi("114", DayOfWeek.SATURDAY, "12:30"));
	}

	@Test
	public void estaTesoreriaEstaDisponible() {
		terminalTeatroColon = new Terminal("Terminal Teatro Colon", servicios);

		Assert.assertFalse(terminalTeatroColon.estaDisponiblePoi("Tesoreria", DayOfWeek.SUNDAY, "11:30"));
	}

}