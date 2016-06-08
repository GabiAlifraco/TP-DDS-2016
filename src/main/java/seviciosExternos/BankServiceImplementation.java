package seviciosExternos;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import DTOs.BancoDTO;
import json.JsonFactory;

public class BankServiceImplementation implements BankService {
	private JsonFactory jsonFactory = new JsonFactory();
	private Client client = Client.create();
	private static final String API = "http://private-96b476-ddsutn.apiary-mock.com/";
	private static final String RESOURCE = "banks";

	public List<BancoDTO> getSucursalesBancosByNombreBanco(String nombreBanco, String tipoServicio) {
		WebResource recurso = this.client.resource(API).path(RESOURCE);
		WebResource recursoConParametros = recurso.queryParam("banco=", nombreBanco + "&servicio=" + tipoServicio);
		WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);

		return mapearJsonABancoDTO(response);

	}

	private List<BancoDTO> mapearJsonABancoDTO(ClientResponse response){
		BancoDTO[] bancosDTO = jsonFactory.fromJson(response.getEntity(String.class), BancoDTO[].class);
		return Arrays.asList(bancosDTO);

	}
	
}