package MocksServicios;

import java.util.Arrays;
import java.util.List;

import DTOs.BancoDTO;
import json.JsonFactory;
import seviciosExternos.BankService;

public class MockBankService implements BankService {

	private JsonFactory jsonFactory = new JsonFactory();

	@Override
	public List<BancoDTO> getSucursalesBancosByNombreBanco(String nombreBanco, String nombreServicio) {
		
				BancoDTO[] bancosDTO = jsonFactory.fromJson("[{"+"\"banco\": \"Banco de la Plaza\","+"\"x\": -35.9338322,"+"\"y\": 72.348353,"+"\"sucursal\": \"Avellaneda\","+"\"gerente\": \"Javier Loeschbor\","+"\"servicios\": [ \"cobro cheques\", \"depositos\", \"extracciones\", \"transferencias\", \"creditos\" ] "+"},"+"{"+"\"banco\": \"Banco de la Plaza\","+"\"x\": -35.9345681,"+"\"y\": 72.344546,"+"\"sucursal\": \"Caballito\","+"\"gerente\": \"Fabian Fantaguzzi\","+"\"servicios\": [ \"depositos\", \"extracciones\", \"transferencias\", \"seguros\" ]"+"}]", BancoDTO[].class);
		return Arrays.asList(bancosDTO);

	}

}
