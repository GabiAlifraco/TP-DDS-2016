package TestProcesos;

import java.util.Arrays;
import java.util.List;
import org.junit.*;

import Inicializacion.CreadorDeObjetos;
import Mocks.MockRESTBajas;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Poi;
import Procesos.ProcesoBajaPois;
import Terminal.Terminal;

public class TestProcesoBajaPois extends CreadorDeObjetos {
	private Terminal terminalAbasto;
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);
	ProcesoBajaPois procesoBajaPois;
	MockRESTBajas mockBajas;
	
	@Before
	public void initialize() {
		mockBajas = new MockRESTBajas();
		procesoBajaPois = new ProcesoBajaPois(mockBajas);
		this.crearParada114();
		this.crearBancoSantander();
		this.crearCGPComuna3();
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalAbasto.getBase().getPois().clear();

	}

	@Test
	public void eliminaPoiDeMapa() {

		terminalAbasto.getBase().agregarUnPoi(comuna3);
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);
		terminalAbasto.getBase().agregarUnPoi(parada114);
		
		List<Poi> resultadoEsperado = Arrays.asList(parada114);
		
		procesoBajaPois.ejecutar();
		Assert.assertTrue(base.getPois().equals(resultadoEsperado));
	}
	

}
