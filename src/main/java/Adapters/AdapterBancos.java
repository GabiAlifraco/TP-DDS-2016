package Adapters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;
import DTOs.BancoDTO;
import Pois.Banco;
import json.JsonFactory;
import seviciosExternos.BankService;

public class AdapterBancos {

	BankService servicioBanco;
	JsonFactory jsonFactory = new JsonFactory();

	public List<Banco> buscarBancos(String nombreBanco, String servicio) {

		BancoDTO[] bancosDTO = jsonFactory.fromJson(servicioBanco.getSucursalesBancosByNombreBanco(nombreBanco, servicio),
				BancoDTO[].class);
		List<BancoDTO> bancosEncontrados = Arrays.asList(bancosDTO);

		return bancosEncontrados.stream()
				.map(bancoEncontrado -> mapearBancoDTOABanco(bancoEncontrado))
				.collect(Collectors.toList());

	}

	public Banco mapearBancoDTOABanco(BancoDTO bancoDTO) {

		double coordenadaX = Double.parseDouble(bancoDTO.getX());
		double coordenadaY = Double.parseDouble(bancoDTO.getY());

		Point coordenadas = new Point(coordenadaX, coordenadaY);
		Banco banco = new Banco(bancoDTO.getBanco(),coordenadas, bancoDTO.getServicios());
		return banco;
	}

	public void setBankService(BankService servicio) {
		servicioBanco = servicio;
	}
}
