package TestServiciosExternos;

import java.time.DayOfWeek;
import java.util.Arrays;
import org.junit.*;
import Inicializacion.CreadorDeObjetos;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.CGP;

public class TestMappercCGP extends CreadorDeObjetos {
	
	ProveedorCGPs adapterCGP = new ProveedorCGPs();
	private CGP cgpMapeado;
	
	
	@Test
	public void testCentroDTOMapeadoaCGP(){
		this.crearCentroDTO();
		cgpMapeado = adapterCGP.deCentroDTOaCGP(centroDTO);
		Assert.assertTrue(cgpMapeado.getNombre().equals("CGP Comuna 3"));
		Assert.assertTrue(cgpMapeado.getRegion().getBarrio().equals("Balvanera, San Cristóbal"));
		Assert.assertTrue(cgpMapeado.getServicios().get(0).getNombre().equals("Infracciones"));
		Assert.assertTrue(cgpMapeado.getServicios().get(1).getNombre().equals("Atención ciudadana"));
		Assert.assertTrue(cgpMapeado.getServicios().size() == 2);
		Assert.assertTrue(cgpMapeado.getServicios().get(0).getHorariosDeAtencion().get(0).getDias().equals(Arrays.asList(DayOfWeek.of(1))));
	}
	
}
