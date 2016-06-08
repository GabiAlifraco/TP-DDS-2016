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
import seviciosExternos.BankServiceImplementation;

public class TestAdapterBanco {

	
	ProveedorBancos adapterBanco = new ProveedorBancos();
	BankService servicio = new BankServiceImplementation();
	
	BaseDePois base = BaseDePois.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base, adapterBanco);
	
	Terminal terminal = new Terminal("Terminal abasto", servicios);
	
	@Test
	public void testJSONConvertidoABanco(){
		
		adapterBanco.setBankService(servicio);
		List<Poi> bancos = new ArrayList<Poi>(); 
		bancos = adapterBanco.buscarPois("NOMBREDEBANCO", "TIPODESERVICIO");
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertTrue(bancos.get(0).getPalabrasClave().size() == 5);
		Assert.assertTrue(bancos.get(0).getPalabrasClave().get(0).equals("cobro cheques"));
		
	}
	
	public void busquedaPoisDesdeTerminal(){
		
		adapterBanco.setBankService(servicio);
		List<Poi> bancos = new ArrayList<Poi>(); 
		bancos = terminal.busquedaDePuntos("NOMBREDEBANCO", "TIPODESERVICIO");
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertTrue(bancos.get(0).getPalabrasClave().size() == 5);
		Assert.assertTrue(bancos.get(0).getPalabrasClave().get(0).equals("cobro cheques"));
	
	}
}

