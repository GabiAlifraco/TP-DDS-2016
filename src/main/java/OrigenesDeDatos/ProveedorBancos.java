package OrigenesDeDatos;

import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
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
        
		Domicilio domicilio = new Domicilio("",0,"","",0,0,0,0,0);
		Region region = new Region("","","","");
		Point coordenadas = new Point(coordenadaX, coordenadaY);
		Ubicacion ubicacion = new Ubicacion(domicilio,region,coordenadas);
		
		Banco banco = new Banco(bancoDTO.getBanco(), bancoDTO.getServicios(),ubicacion);
		return banco;
	}

	public void setBankService(BankService servicio) {
		servicioBanco = servicio;
	}
}
