package TestServiciosExternos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import CaracteristicaPoi.ServicioCGP;
import MocksServicios.MockedCGPService;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.Poi;
import Terminal.Terminal;
import seviciosExternos.CGPService;

public class TestServicioCGP {
	ProveedorCGPs proveedorCGPs = new ProveedorCGPs();
	CGPService servicioExternoCGP = new MockedCGPService();
	
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base, proveedorCGPs);
	
	Terminal terminal = new Terminal("Terminal Villa Crespo", servicios);
	
	List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
	

	@Test
	public void buscarCGPAdapterCGP() {
		proveedorCGPs.setServiceCGP(servicioExternoCGP);
		List<Poi> resultado = proveedorCGPs.buscarPois("CGP comuna 15","Villa Crespo");
		Assert.assertTrue(resultado.get(0).getRegion().getBarrio().equals("Villa Crespo"));
	}
	public void buscarPOIxTerminal() {
		proveedorCGPs.setServiceCGP(servicioExternoCGP);
		List<Poi> resultado = terminal.obtenerResultadosServicios("Rentas", "Villa Crespo").stream().collect(Collectors.toList());
		
		Assert.assertTrue(resultado.get(0).getRegion().getBarrio().equals("Villa Crespo"));
	}

}
