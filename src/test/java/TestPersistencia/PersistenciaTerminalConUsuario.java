package TestPersistencia;

	import java.util.ArrayList;
	import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
	import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import com.mongodb.MongoClient;

import Accesos.AdministradorUsuarios;
import Accesos.Usuario;
import CaracteristicaPoi.Punto;
	import OrigenesDeDatos.Mapa;
	import OrigenesDeDatos.OrigenDeDatos;
	import OrigenesDeDatos.ProveedorBancos;
	import OrigenesDeDatos.ProveedorCGPs;
import Pois.Poi;
import Terminal.AdministradorTerminales;
	import Terminal.Terminal;

	public class PersistenciaTerminalConUsuario extends AbstractPersistenceTest implements WithGlobalEntityManager {

		Mapa mapa;
		List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
		ProveedorBancos bancos = new ProveedorBancos();
		ProveedorCGPs cgps = new ProveedorCGPs();
		
		
		AdministradorTerminales terminales;
		Punto coordenadas;
		Punto coordenadasVC;
		Terminal terminal;
		Terminal villacrespo;

		Usuario usuarioA;
		Usuario usuarioVC;
		
		AdministradorUsuarios adminUsr;
		

		@Before
		public void initialize() {
			//CORRE BOOTSTRAP PARA QUE FUNCIONE

			mapa = Mapa.getInstance();
			servicios.clear();
			servicios.add(mapa);
			servicios.add(bancos);
			servicios.add(cgps);
			usuarioA= new Usuario("terminal5Abastooooooo","abasto1223","terminal");
			terminal = new Terminal("Terminal Abasto", servicios,usuarioA);
			coordenadas = new Punto(-34.6030, -58.4107);
			terminal.setCoordenadaDispositivoMovil(coordenadas);
			terminal.setComunaTerminal("3");
			
			usuarioVC= new Usuario("terminalVC","elmejorbarrio123","terminal");
			villacrespo = new Terminal("Terminal Villa Crespo", servicios,usuarioVC);
			coordenadasVC = new Punto(-34.597208, -58.441588);
			villacrespo.setCoordenadaDispositivoMovil(coordenadasVC);
			villacrespo.setComunaTerminal("15");
			adminUsr = new AdministradorUsuarios();
			terminales = new AdministradorTerminales();
			/*terminales.agregarTerminal(terminal);
			terminales.agregarTerminal(villacrespo);*/
		}

		@Test
		public void testPersistenciaTerminal2() {
			List<Terminal> terminales2 =entityManager().createQuery("from Terminal", Terminal.class).getResultList();
			Assert.assertEquals("terminalVC",terminales2.get(1).getUsuario().getUser());
		}
		@Test
		public void testPersistenciaTerminal() {
		Usuario usuarioBase = adminUsr.buscarUsuario("terminalAbasto");
		System.out.println();
		String rol = usuarioBase.getRole().toLowerCase(); 
		String hashRol = DigestUtils.sha1Hex(rol);
		String nombreUsuario = usuarioBase.getUser();
		Terminal terminal=terminales.listar().stream().filter(t -> t.getUsuario().getUser().equals(usuarioBase.getUser())).collect(Collectors.toList()).get(0);
		String nt = terminal.getNombreTerminal();
		Terminal terminal2 = terminales.listar().stream().filter(t->t.getNombreTerminal().equals(nt)).collect(Collectors.toList()).get(0);
		List<Poi> pois =terminal2.getBase().buscarPois("cgp", "CABA");
		List<Poi> poisaux = terminal2.getBase().buscarPorTipo("CGP");
		System.out.println(rol);
		System.out.println(hashRol);
		System.out.println(nombreUsuario);
		System.out.println(nt);
		System.out.println(terminal2.getNombreTerminal());
		System.out.println(String.valueOf(pois.size()));
		System.out.println(String.valueOf(poisaux.size()));
		}
}
