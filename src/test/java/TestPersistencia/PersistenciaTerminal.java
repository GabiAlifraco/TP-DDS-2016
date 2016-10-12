package TestPersistencia;

import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Terminal.Terminal;

public class PersistenciaTerminal extends AbstractPersistenceTest implements WithGlobalEntityManager {

	Mapa mapa = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(mapa);
	Point coordenadas;
	
	Terminal terminal;

	@Before
	public void initialize(){
	
		terminal = new Terminal("Terminal Abasto", servicios);
		coordenadas = new Point(-34.6030, -58.4107);
		terminal.setCoordenadaDispositivoMovil(coordenadas);
		terminal.setComunaTerminal("3");
		entityManager().persist(terminal);
	}
	
	@Test
	public void testPersistenciaTerminal(){
		Assert.assertEquals(entityManager().find(Terminal.class, terminal.getIdTerminal()), terminal);
	}
	
	public void testPersistenciaCoordenadas(){
		Assert.assertEquals(entityManager().find(Terminal.class, 
				terminal.getIdTerminal()).getCoordenadaDispositivoMovil(), coordenadas);
	}
	
	public void testPersistenciaComuna(){
		Assert.assertEquals(entityManager().find(Terminal.class, 
				terminal.getIdTerminal()).getComunaTerminal(), "3");
	}
	
	public void testPersistenciaNombre(){
		Assert.assertEquals(entityManager().find(Terminal.class, 
				terminal.getIdTerminal()).getNombreTerminal(), "Terminal Abasto");
	}
}
