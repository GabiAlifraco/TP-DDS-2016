package seviciosExternos;

import com.sun.jersey.api.client.ClientResponse;

public interface BankService {

	public ClientResponse getSucursalesBancosByNombreBanco(String nombreBanco, String nombreServicio);
}
