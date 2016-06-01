package seviciosExternos;

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MockedBankService implements BankService {

	private Client client = Client.create();
	private static final String API = "http://private-96b476-ddsutn.apiary-mock.com/";
	private static final String RESOURCE = "banks";

	public ClientResponse getSucursalesBancosByNombreBanco(String nombreBanco, String tipoServicio) {
		WebResource recurso = this.client.resource(API).path(RESOURCE);
		WebResource recursoConParametros = recurso.queryParam("banco=", nombreBanco + "&servicio=" + tipoServicio);
		WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);
		return response;
	}

}