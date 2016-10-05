package TestProcesos;

import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.*;

import Inicializacion.CreadorDeObjetos;
import MocksProcesos.MockAlmacenadorResultados;
import MocksServicios.MockRESTBajas;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Poi;
import PoliticasReejecucion.NingunaAccion;
import PoliticasReejecucion.Politica;
import Procesos.Almacenador;
import Procesos.ProcesoBajaPois;
import Terminal.Terminal;

public class TestProcesoBajaPois extends CreadorDeObjetos {
	private Terminal terminalAbasto;
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);
	ProcesoBajaPois procesoBajaPois;
	MockRESTBajas mockBajas;
	Politica politica;
	Almacenador almacenador;

	@Before
	public void initialize() {
		String cronogramaEjecucion = "2016-09-11T15:32";
		LocalDateTime cronograma = LocalDateTime.parse(cronogramaEjecucion);
		mockBajas = new MockRESTBajas();
		politica = new NingunaAccion();
		almacenador = new MockAlmacenadorResultados();
		
		procesoBajaPois = new ProcesoBajaPois(cronograma, mockBajas, politica, almacenador);
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

		Assert.assertFalse(base.getPois().equals(resultadoEsperado));
	}

}
