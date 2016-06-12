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
	

	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);

	@Before
	public void initialize() {
		this.crearBancoSantander();
		this.crearParada114();
		this.crearCGPComuna3();
		this.crearCarrouselPlinPlin();
		}

	@Test
	public void estaElCarrouselDisponible() {
		Terminal terminalFlorida = new Terminal("Terminal Florida", servicios);
		terminalFlorida.getBase().getPois().clear();
		terminalFlorida.getBase().getPois().add(parada114);
		terminalFlorida.getBase().getPois().add(bancoSantander);
		terminalFlorida.getBase().getPois().add(carrouselPlinPlin);
		terminalFlorida.getBase().getPois().add(comuna3);

		Assert.assertTrue(terminalFlorida.estaDisponiblePoi("Carrousel PlinPlin", DayOfWeek.SATURDAY, "12:30"));

	}

	@Test
	public void estaElBancoDisponible() {

		Terminal terminalAbasto = new Terminal("Terminal Abasto", servicios);

		terminalAbasto.getBase().getPois().clear();
		terminalAbasto.getBase().getPois().add(parada114);
		terminalAbasto.getBase().getPois().add(bancoSantander);
		terminalAbasto.getBase().getPois().add(carrouselPlinPlin);
		Assert.assertFalse(terminalAbasto.estaDisponiblePoi("Banco Santander", DayOfWeek.TUESDAY, "20:00"));
	}

	@Test
	public void estaElColectivoDisponible() {
		Terminal terminalCampus = new Terminal("Terminal Campus", servicios);
		terminalCampus.getBase().getPois().clear();
		terminalCampus.getBase().getPois().add(parada114);
		terminalCampus.getBase().getPois().add(bancoSantander);
		terminalCampus.getBase().getPois().add(carrouselPlinPlin);
		Assert.assertTrue(terminalCampus.estaDisponiblePoi("114", DayOfWeek.SATURDAY, "12:30"));
	}

	@Test
	public void estaTesoreriaEstaDisponible() {
		Terminal terminalTeatroColon = new Terminal("Terminal Teatro Colon", servicios);
		terminalTeatroColon.getBase().getPois().clear();
		terminalTeatroColon.getBase().getPois().add(parada114);
		terminalTeatroColon.getBase().getPois().add(bancoSantander);
		terminalTeatroColon.getBase().getPois().add(carrouselPlinPlin);
		terminalTeatroColon.getBase().getPois().add(comuna3);
		Assert.assertFalse(terminalTeatroColon.estaDisponiblePoi("Tesoreria", DayOfWeek.SUNDAY, "11:30"));
	}

}