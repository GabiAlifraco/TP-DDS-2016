package TestAdapters;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import Adapters.AdapterCGP;
import OrigenesDeDatos.BaseDePois;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.CGP;
import Pois.Poi;
import Pois.ServicioCGP;
import TpAnual.Terminal;
import seviciosExternos.CGPService;
import seviciosExternos.MockedCGPService;

public class TestMockedCGPService {
	AdapterCGP adapterCGP = new AdapterCGP();
	CGPService servicioExternoCGP = new MockedCGPService();
	
	BaseDePois base = BaseDePois.getInstance();
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
