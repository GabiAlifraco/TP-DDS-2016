package TestPersistencia;

import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Terminal.AdministradorUsuarios;
import Terminal.Terminal;

public class PersistenciaTerminal extends AbstractPersistenceTest implements WithGlobalEntityManager {

	Mapa mapa = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(mapa);
	Point coordenadas;
	AdministradorUsuarios usuarios;

	Terminal terminal;

	@Before
	public void initialize() {

		terminal = new Terminal("Terminal Abasto", servicios);
		coordenadas = new Point(-34.6030, -58.4107);
		terminal.setCoordenadaDispositivoMovil(coordenadas);
		terminal.setComunaTerminal("3");
		usuarios = new AdministradorUsuarios();
		usuarios.agregarTerminal(terminal);
	}

	@Test
	public void testPersistenciaTerminal() {
		Assert.assertEquals(usuarios.buscarTerminal(terminal.getIdTerminal()), terminal);
	}

	public void testPersistenciaCoordenadas() {
		Assert.assertEquals(usuarios.buscarTerminal(terminal.getIdTerminal()).getCoordenadaDispositivoMovil(),
				coordenadas);
	}

	public void testPersistenciaComuna() {
		Assert.assertEquals(usuarios.buscarTerminal(terminal.getIdTerminal()).getComunaTerminal(), "3");
	}

	public void testPersistenciaNombre() {
		Assert.assertEquals(usuarios.buscarTerminal(terminal.getIdTerminal()).getNombreTerminal(), "Terminal Abasto");
	}

	public void testModificacionTerminal() {
		usuarios.modificarComunaTerminal(terminal.getIdTerminal(), "14");
		Point coordenadasModificadas = new Point(-34.5879, -58.4100);
		usuarios.modificarCoordenadaTerminal(terminal.getIdTerminal(), coordenadasModificadas);
		usuarios.modificarNombreTerminal(terminal.getIdTerminal(), "Terminal Alto Palermo");

		Assert.assertEquals(usuarios.buscarTerminal(terminal.getIdTerminal()).getCoordenadaDispositivoMovil(),
				coordenadasModificadas);
		Assert.assertEquals(usuarios.buscarTerminal(terminal.getIdTerminal()).getComunaTerminal(), "14");
		Assert.assertEquals(usuarios.buscarTerminal(terminal.getIdTerminal()).getNombreTerminal(),
				"Terminal Alto Palermo");
	}
	
	public void testEliminarTerminal(){
		usuarios.eliminarTerminal(terminal);
		Assert.assertEquals(usuarios.buscarTerminal(terminal.getIdTerminal()), null);
	}
}
