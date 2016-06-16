package OrigenesDeDatos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import Pois.CGP;
import Pois.Poi;
import seviciosExternos.CGPService;
import java.util.stream.Collectors;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.ServicioCGP;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ProveedorCGPs implements OrigenDeDatos {
	CGPService serviceCGP;

	public List<Poi> buscarPois(String unNombre, String unaPalabraClave) {
		
		List<Poi> listaCGPs = new ArrayList<Poi>();
		listaCGPs.add(buscarCGPs(unaPalabraClave));

		return listaCGPs;
	}

	private CGP buscarCGPs(String calleOBarrio) {

		CentroDTO centroDTOEncontrado = serviceCGP.getCGPsByCalleOBarrio(calleOBarrio);

		return deCentroDTOaCGP(centroDTOEncontrado);

	}

	public CGP deCentroDTOaCGP(CentroDTO centroDTO) {
		
		CGP unCGP = new CGP(String.valueOf(centroDTO.getNumeroComuna()), centroDTO.getZonasIncluidas(),
				obtenerServiciosCGP(centroDTO));
		return unCGP;
	}

	private List<ServicioCGP> obtenerServiciosCGP(CentroDTO centroDTO) {
		
		List<ServicioCGP> serviciosCGP = centroDTO.getServiciosDTO().stream().map(servicioDTO -> this.deServicioDTOaServicioCGP(servicioDTO)).collect(Collectors.toList());
		return serviciosCGP;
	}
	private ServicioCGP deServicioDTOaServicioCGP(ServicioDTO servicioDTO) {
		
		ServicioCGP servicioCGP = new ServicioCGP(servicioDTO.getNombreServicio(), this.deRangosADisponibilidades(servicioDTO.getRangosDTO()));
		
		return servicioCGP;
	}
	private List<Disponibilidad> deRangosADisponibilidades (List<RangoServicioDTO> rangos){
		return rangos.stream().map(rango -> this.aDisponibilidad(rango)).collect(Collectors.toList());
	}

	private Disponibilidad aDisponibilidad(RangoServicioDTO rango) {
		
		LocalTime horarioInicial = LocalTime.of(rango.getHorarioDesde(),rango.getMinutosDesde());
		LocalTime horarioCierre = LocalTime.of(rango.getHorarioHasta(),rango.getMinutosHasta());
		List<DayOfWeek> dia = Arrays.asList(DayOfWeek.of(rango.getNumeroSemana()));
		Disponibilidad unaDisponibilidadCGP = new Disponibilidad(dia,horarioInicial, horarioCierre);
		
		return unaDisponibilidadCGP;
	}

	public void setServiceCGP(CGPService serviceCGP) {
		this.serviceCGP = serviceCGP;
	}

}