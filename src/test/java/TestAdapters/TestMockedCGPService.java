package TestAdapters;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import Adapters.AdapterCGP;
import Pois.CGP;
import TpAnual.BaseDePois;
import TpAnual.OrigenDeDatos;
import TpAnual.Poi;
import TpAnual.ServicioCGP;
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
		List<Poi> resultado = terminal.obtenerResultadosServicios("Rentas", "Villa Crespo");
		
		Assert.assertTrue(resultado.get(0).getRegion().getBarrio().equals("Villa Crespo"));
	}
	public void definirNombreTest() {
		adapterCGP.setServiceCGP(servicioExternoCGP);
		List<Poi> resultado = terminal.obtenerResultadosServicios("Rentas", "Villa Crespo");
		
		Assert.assertTrue(resultado.get(0).getRegion().getBarrio().equals("Villa Crespo"));
	}

}
