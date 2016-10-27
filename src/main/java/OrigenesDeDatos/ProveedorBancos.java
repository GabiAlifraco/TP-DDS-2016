package OrigenesDeDatos;

import java.util.List;
import java.util.stream.Collectors;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import DTOs.BancoDTO;
import Pois.Banco;
import Pois.Poi;
import cacheServicios.CacheBancos;
import seviciosExternos.BankService;

public class ProveedorBancos implements OrigenDeDatos {

	private BankService servicioBanco;
	private CacheBancos cache;


	public List<Poi> buscarPois(String nombreBanco, String servicio) {
		List<Poi> bancosCache = cache.buscar(nombreBanco, servicio);

		if (bancosCache.isEmpty()) {

			List<BancoDTO> bancosEncontrados = servicioBanco.getSucursalesBancosByNombreBanco(nombreBanco, servicio);
			List<Poi> bancosServicio = bancosEncontrados.stream().map(bancoEncontrado -> mapearBancoDTOABanco(bancoEncontrado))
					.collect(Collectors.toList());

			cache.guardar(bancosServicio, nombreBanco, servicio);
			
			return bancosServicio;
		} else {
			return bancosCache;
		}

	}

	private Banco mapearBancoDTOABanco(BancoDTO bancoDTO) {

		double coordenadaX = Double.parseDouble(bancoDTO.getX());
		double coordenadaY = Double.parseDouble(bancoDTO.getY());

		Domicilio domicilio = new Domicilio("", 0, "", "", 0, 0, 0, 0, 0);
		Region region = new Region("", "", "", "");
		Punto coordenadas = new Punto(coordenadaX, coordenadaY);
		Ubicacion ubicacion = new Ubicacion(domicilio, region, coordenadas);

		Banco banco = new Banco(bancoDTO.getBanco(), bancoDTO.getServicios(), ubicacion);
		return banco;
	}

	public void setBankService(BankService servicio) {
		servicioBanco = servicio;
	}
	public CacheBancos getCache() {
		return cache;
	}
	
	public void setCache(CacheBancos cache) {
		this.cache = cache;
	}
}
