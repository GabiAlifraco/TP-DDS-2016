package TestProcesos;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.nio.file.*;
import Procesos.ProcesoActualizarPalabrasClave;
import Inicializacion.CreadorDeObjetos;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Terminal.Terminal;

public class TestProcesoActualizarPalabrasClave extends CreadorDeObjetos {
	private Terminal terminalAbasto;
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);
	private Path archivo;
	private ProcesoActualizarPalabrasClave actualizarPalabrasClave;
	private List<String> lineas =Arrays.asList("Carrousel PlinPlin;colegio escolar uniformes modas", "El diario del pueblo;Revistas Diarios Crucigrama Peliculas","El kiosco de Pepe;Revistas Crucigrama");

	@Before
	public void initialize() throws IOException{
		archivo = Paths.get("actualizarPalabrasClave.txt");
		Files.write(archivo, lineas);
		this.crearCarrouselPlinPlin();
		this.crearElDiarioDelPueblo();
		this.crearKioscoPepe();
		actualizarPalabrasClave = new ProcesoActualizarPalabrasClave(archivo);
		terminalAbasto = new Terminal("Terminal Abasto",servicios);
		terminalAbasto.getBase().getPois().clear();
		archivo.toFile().deleteOnExit();
	}
	@Test
	public void testActualizaCarrousel() throws IOException{
		
		terminalAbasto.getBase().agregarUnPoi(KioscoPepe);
		terminalAbasto.getBase().agregarUnPoi(elDiarioDelPueblo);
		terminalAbasto.getBase().agregarUnPoi(carrouselPlinPlin);
		actualizarPalabrasClave.ejecutar();
		List<String> palabrasEsperadas = Arrays.asList("colegio","escolar","uniformes","modas");
		Assert.assertTrue(base.getPois().get(2).getPalabrasClave().equals(palabrasEsperadas));
		
		
	}
	@Test
	public void testActualizaElDiarioDelPueblo() throws IOException{
		
		terminalAbasto.getBase().agregarUnPoi(KioscoPepe);
		terminalAbasto.getBase().agregarUnPoi(elDiarioDelPueblo);
		terminalAbasto.getBase().agregarUnPoi(carrouselPlinPlin);
		actualizarPalabrasClave.ejecutar();
		List<String> palabrasEsperadas = Arrays.asList("Revistas","Diarios","Crucigrama","Peliculas");
		Assert.assertTrue(base.getPois().get(1).getPalabrasClave().equals(palabrasEsperadas));
		
	}
	@Test
	public void testNoActualizoKioscoPepe() throws IOException{
		
		terminalAbasto.getBase().agregarUnPoi(KioscoPepe);
		terminalAbasto.getBase().agregarUnPoi(elDiarioDelPueblo);
		terminalAbasto.getBase().agregarUnPoi(carrouselPlinPlin);
		actualizarPalabrasClave.ejecutar();
		List<String> palabrasNoEsperadas = Arrays.asList("Revistas","Crucigrama");
		Assert.assertFalse(base.getPois().get(0).getPalabrasClave().equals(palabrasNoEsperadas));
		
	}
	@Test
	public void testNoFinalizaOK() throws IOException{
		
		terminalAbasto.getBase().agregarUnPoi(KioscoPepe);
		terminalAbasto.getBase().agregarUnPoi(elDiarioDelPueblo);
		terminalAbasto.getBase().agregarUnPoi(carrouselPlinPlin);
		archivo.toFile().setExecutable(false);
		actualizarPalabrasClave.ejecutar ();
		Assert.assertFalse(actualizarPalabrasClave.finalizoOK);
		
	}
}
