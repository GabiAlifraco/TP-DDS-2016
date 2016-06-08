package TestAdapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import OrigenesDeDatos.BaseDePois;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorBancos;
import Pois.Poi;
import TpAnual.Terminal;
import seviciosExternos.BankService;
import seviciosExternos.MockBankService;

public class TestAdapterBanco {

	
	ProveedorBancos proveedorBancos = new ProveedorBancos();
	BankService servicio = new MockBankService();
	
	BaseDePois base = BaseDePois.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base, proveedorBancos);
	
	Terminal terminal = new Terminal("Terminal abasto", servicios);
	
	@Test
	public void testJSONMapeadoABanco(){
		
		proveedorBancos.setBankService(servicio);
		List<Poi> bancos = new ArrayList<Poi>(); 
		bancos = proveedorBancos.buscarPois("Banco", "Servicio");
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertTrue(bancos.get(0).getPalabrasClave().size() == 5);
		Assert.assertTrue(bancos.get(0).getPalabrasClave().get(0).equals("cobro cheques"));
		
	}
	public void busquedaPoisDesdeTerminal(){
		
		proveedorBancos.setBankService(servicio);
		List<Poi> bancos = new ArrayList<Poi>(); 
		bancos = terminal.busquedaDePuntos("NOMBREDEBANCO", "TIPODESERVICIO");
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertTrue(bancos.get(0).getPalabrasClave().size() == 5);
		Assert.assertTrue(bancos.get(0).getPalabrasClave().get(0).equals("cobro cheques"));
	
	}
}

