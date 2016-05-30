package TestAdapters;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import Adapters.AdapterBancos;
import Pois.Banco;
import seviciosExternos.BankService;
import seviciosExternos.MockedBankService;

public class TestAdapterBanco {

	
	AdapterBancos adapterBanco = new AdapterBancos();
	BankService servicio = new MockedBankService();
	
	@Test
	public void testJSONConvertidoABanco(){
		
		adapterBanco.setBankService(servicio);
		List<Banco> bancos = new ArrayList<Banco>(); 
		bancos = adapterBanco.buscarBancos("NOMBREDEBANCO", "TIPODESERVICIO");
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertTrue(bancos.get(0).getPalabrasClave().size() == 5);
		Assert.assertTrue(bancos.get(0).getPalabrasClave().get(0).equals("cobro cheques"));
		
	}
	
	
}

