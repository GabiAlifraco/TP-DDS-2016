package TestProcesos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import MocksProcesos.MockAlmacenadorResultados;
import MocksProcesos.MockProceso;
import PoliticasReejecucion.NotificacionFalloProceso;
import PoliticasReejecucion.ReejecucionProceso;
import Procesos.Almacenador;
import Procesos.Proceso;
import Procesos.ResultadoEjecucion;

public class TestPoliticas {

	ReejecucionProceso reejecucion;
	NotificacionFalloProceso notificar;
	MockProceso mockProceso;
	Proceso procesoBajaPois;
	Almacenador mockAlmacenador;
	String cronogramaEjecucion;
	
	@Before
	public void initialize(){
		mockAlmacenador = new MockAlmacenadorResultados();
		mockProceso = new MockProceso(mockAlmacenador);
		cronogramaEjecucion = "2017-09-11T15:32";
		mockAlmacenador.getResultadosProcesos().clear();

	}
	
	@Test
	public void testReejecucionProceso(){
		reejecucion = new ReejecucionProceso(1, false);
		reejecucion.aplicarPolitica(mockProceso);
		
		Assert.assertTrue(mockProceso.ejecutoProceso());
		Assert.assertTrue(reejecucion.getCantidadReejecucionesRealizadas() == 1);
	}
	public void testNoReejecutaProceso(){
		reejecucion = new ReejecucionProceso(0, false);
		reejecucion.aplicarPolitica(mockProceso);
		
		Assert.assertFalse(mockProceso.ejecutoProceso());
	}

	public void testProcesoAlmacenaResultado(){
		reejecucion = new ReejecucionProceso(1, false);
		reejecucion.aplicarPolitica(mockProceso);
		
		ResultadoEjecucion resultado = mockAlmacenador.getResultadosProcesos().get(0);
		
		Assert.assertTrue(resultado.finalizoOK());
		Assert.assertTrue(resultado.getProceso().equals(mockProceso));
		Assert.assertTrue(resultado.getCantidadElementosAfectados() == 0);
				
	}
	
	public void testNotificaAdministrador(){
		notificar = new NotificacionFalloProceso();
		notificar.aplicarPolitica(mockProceso);
		
		Assert.assertTrue(notificar.administradorNotificado);
	}
	
	public void testNotificaAdministradorPorCantidadFallos(){
		reejecucion = new ReejecucionProceso(0, false);
		reejecucion.aplicarPolitica(mockProceso);
		
		Assert.assertTrue(notificar.administradorNotificado);
	}
	
}
