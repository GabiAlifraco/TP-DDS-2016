package TestProcesos;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Inicializacion.CreadorDeObjetos;
import MocksProcesos.MockAlarma;
import MocksProcesos.MockAlmacenadorResultados;
import MocksProcesos.MockTimer;
import MocksServicios.MockRESTBajas;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Poi;
import PoliticasReejecucion.NingunaAccion;
import PoliticasReejecucion.Politica;
import Procesos.Alarma;
import Procesos.Almacenador;
import Procesos.PlanificadorProcesos;
import Procesos.ProcesoBajaPois;
import Terminal.Terminal;

public class TestPlanificador extends CreadorDeObjetos {

	private Terminal terminalAbasto;
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);
	ProcesoBajaPois procesoBajaPois;
	MockRESTBajas mockBajas;
	MockAlarma mockAlarma;
	MockTimer mockTimer;
	Almacenador almacenador;
	Politica politica;
	PlanificadorProcesos planificador;

	@Before
	public void initialize() {
		String cronogramaEjecucion = "2017-09-11T15:32";
		LocalDateTime cronograma = LocalDateTime.parse(cronogramaEjecucion);

		mockBajas = new MockRESTBajas();
		politica = new NingunaAccion();
		almacenador = new MockAlmacenadorResultados();
		
		procesoBajaPois = new ProcesoBajaPois(cronograma, mockBajas, politica, almacenador);

		this.crearParada114();
		this.crearBancoSantander();
		this.crearCGPComuna3();

		mockAlarma = new MockAlarma();
		mockTimer = new MockTimer();

		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalAbasto.getBase().getPois().clear();

	}

	@Test
	public void testAlarmaSeteada() {
		planificador = new PlanificadorProcesos();
		Timer timer = new Timer();
		planificador.setTimer(timer);
		planificador.setAlarmaProceso(mockAlarma);
		planificador.planificarProceso(procesoBajaPois);

		Assert.assertTrue(mockAlarma.alarmaSeteada());
	}

	@Test
	public void testTimerSeteado() {

		planificador = new PlanificadorProcesos();
		planificador.setTimer(mockTimer);
		Alarma alarma = new Alarma();
		alarma.setAlarma(procesoBajaPois, planificador);
		planificador.setAlarmaProceso(alarma);
		planificador.planificarProceso(procesoBajaPois);

		Assert.assertTrue(mockTimer.alarmaPlanificada());
	}

	@Test
	public void testAlarmaEjecutaProceso() {
		planificador = new PlanificadorProcesos();
		Alarma alarma = new Alarma();
		alarma.setAlarma(procesoBajaPois, planificador);

		terminalAbasto.getBase().agregarUnPoi(comuna3);
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);
		terminalAbasto.getBase().agregarUnPoi(parada114);

		List<Poi> resultadoEsperado = Arrays.asList(parada114);

		alarma.run();

		Assert.assertFalse(base.getPois().equals(resultadoEsperado));

	}


}
