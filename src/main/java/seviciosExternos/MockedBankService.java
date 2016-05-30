package seviciosExternos;

import java.net.MalformedURLException;
import java.net.URL;

public class MockedBankService implements BankService {

	@Override
	public URL getSucursalesBancosByNombreBanco(String nombreBanco, String nombreServicio) {
		String urlACrear = "http://private-96b476-ddsutn.apiary-mock.com/banks?banco="+nombreBanco+"&servicio"+nombreServicio; 
		try {
			return new URL(urlACrear);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
}
}