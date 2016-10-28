package OrigenesDeDatos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import Pois.CGP;
import Pois.Poi;
import cacheServicios.CacheCGPs;
import seviciosExternos.CGPService;
import java.util.stream.Collectors;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ProveedorCGPs implements OrigenDeDatos {
	CGPService serviceCGP;
	private CacheCGPs cache;



	public List<Poi> buscarPois(String unNombre, String unaPalabraClave) {
		List<Poi> cgpsCache = cache.buscar(unaPalabraClave);
		
		if (cgpsCache.isEmpty()) {
			List<Poi> listaCGPs = new ArrayList<Poi>();
			listaCGPs.add(buscarCGPs(unaPalabraClave));
			cache.guardar(listaCGPs, unaPalabraClave);
		
			return listaCGPs;
		} else {
			
			return cgpsCache;
		}
	}

	private CGP buscarCGPs(String calleOBarrio) {
		CentroDTO centroDTOEncontrado = serviceCGP.getCGPsByCalleOBarrio(calleOBarrio);
		return deCentroDTOaCGP(centroDTOEncontrado);

	}

	public CGP deCentroDTOaCGP(CentroDTO centroDTO) {
		String calle = this.getCalleYAltura(centroDTO)[0];
		int altura = Integer.parseInt(this.getCalleYAltura(centroDTO)[1]);
		Domicilio domicilio = new Domicilio(calle, altura, "", "", 0, 0, 0, 0, 0);
		Region region = new Region("CABA", centroDTO.getZonasIncluidas(), "Buenos Aires", "Argentina");
		Punto coordenadas = new Punto(0, 0);
		Ubicacion ubicacion = new Ubicacion(domicilio, region, coordenadas);
		CGP unCGP = new CGP(String.valueOf(centroDTO.getNumeroComuna()), centroDTO.getZonasIncluidas(),
				obtenerServiciosCGP(centroDTO), ubicacion);
		return unCGP;
	}

	private List<ServicioCGP> obtenerServiciosCGP(CentroDTO centroDTO) {
		List<ServicioCGP> serviciosCGP = centroDTO.getServiciosDTO().stream()
				.map(servicioDTO -> this.deServicioDTOaServicioCGP(servicioDTO)).collect(Collectors.toList());
		return serviciosCGP;
	}

	private ServicioCGP deServicioDTOaServicioCGP(ServicioDTO servicioDTO) {
		ServicioCGP servicioCGP = new ServicioCGP(servicioDTO.getNombreServicio(),
				this.deRangosADisponibilidades(servicioDTO.getRangosDTO()));
		return servicioCGP;
	}

	private List<Disponibilidad> deRangosADisponibilidades(List<RangoServicioDTO> rangos) {
		return rangos.stream().map(rango -> this.aDisponibilidad(rango)).collect(Collectors.toList());
	}

	private Disponibilidad aDisponibilidad(RangoServicioDTO rango) {

		LocalTime horarioInicial = LocalTime.of(rango.getHorarioDesde(), rango.getMinutosDesde());
		LocalTime horarioCierre = LocalTime.of(rango.getHorarioHasta(), rango.getMinutosHasta());
		List<DayOfWeek> dia = Arrays.asList(DayOfWeek.of(rango.getNumeroSemana()));
		Disponibilidad unaDisponibilidadCGP = new Disponibilidad(dia, horarioInicial, horarioCierre);

		return unaDisponibilidadCGP;
	}

	public void setServiceCGP(CGPService serviceCGP) {
		this.serviceCGP = serviceCGP;
	}

	private String[] getCalleYAltura(CentroDTO centroDTO) {
		String[] calleyaltura = new String[2];
		String calle = centroDTO.getDomicilioCompletoCGP().split("[\\d]")[0];
		String altura = centroDTO.getDomicilioCompletoCGP()
				.split("[^\\w]")[centroDTO.getDomicilioCompletoCGP().split("[^\\w]").length - 1];
		calleyaltura[0] = calle;
		calleyaltura[1] = altura;

		return calleyaltura;
	}
	public CacheCGPs getCache() {
		return cache;
	}

	public void setCache(CacheCGPs cache) {
		this.cache = cache;
	}
}