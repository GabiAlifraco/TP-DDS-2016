package OrigenesDeDatos;

import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;

import DTOs.BancoDTO;
import Pois.Banco;
import Pois.Poi;
import seviciosExternos.BankService;

public class ProveedorBancos implements OrigenDeDatos {

	private BankService servicioBanco;

	public List<Poi> buscarPois(String nombreBanco, String servicio) {
		List<BancoDTO> bancosEncontrados = servicioBanco.getSucursalesBancosByNombreBanco(nombreBanco, servicio);
		return bancosEncontrados
				.stream()
				.map(bancoEncontrado -> mapearBancoDTOABanco(bancoEncontrado))
				.collect(Collectors.toList());
	}

	private Banco mapearBancoDTOABanco(BancoDTO bancoDTO) {

		double coordenadaX = Double.parseDouble(bancoDTO.getX());
		double coordenadaY = Double.parseDouble(bancoDTO.getY());

		Point coordenadas = new Point(coordenadaX, coordenadaY);
		Banco banco = new Banco(bancoDTO.getBanco(), coordenadas, bancoDTO.getServicios());
		return banco;
	}

	public void setBankService(BankService servicio) {
		servicioBanco = servicio;
	}
}
