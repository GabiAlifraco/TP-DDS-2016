package TestAdapters;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import CaracteristicaPoi.Poi;
import CaracteristicaPoi.ServicioCGP;
import Mocks.MockedCGPService;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.CGP;
import Terminal.Terminal;
import seviciosExternos.CGPService;

public class TestMockedCGPService {
	ProveedorCGPs adapterCGP = new ProveedorCGPs();
	CGPService servicioExternoCGP = new MockedCGPService();
	
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base, adapterCGP);
	
	Terminal terminal = new Terminal("Terminal Villa Crespo", servicios);
	
	List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
	

	@Test
	public void buscarCGPAdapterCGP() {
		adapterCGP.setServiceCGP(servicioExternoCGP);
		CGP resultado = adapterCGP.buscarCGPs("Villa Crespo");
		Assert.assertTrue(resultado.getRegion().getBarrio().equals("Villa Crespo"));
	}
	public void buscarPOIxTerminal() {
		adapterCGP.setServiceCGP(servicioExternoCGP);
		List<Poi> resultado = terminal.obtenerResultadosServicios("Rentas", "Villa Crespo").stream().collect(Collectors.toList());
		
		Assert.assertTrue(resultado.get(0).getRegion().getBarrio().equals("Villa Crespo"));
	}

}
