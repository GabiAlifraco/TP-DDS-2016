package Adapters;

import java.util.ArrayList;
import java.util.List;
import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.CGP;
import Pois.Poi;
import Pois.ServicioCGP;
import TpAnual.Disponibilidad;
import seviciosExternos.CGPService;
import java.util.stream.Collectors;
import java.time.DayOfWeek;

public class AdapterCGP implements OrigenDeDatos {
	CGPService serviceCGP;

	public List<Poi> buscarPois(String unNombre, String unaPalabraClave) {

		List<Poi> listaCGPs = new ArrayList<Poi>();
		listaCGPs.add(buscarCGPs(unaPalabraClave));

		return listaCGPs;
	}

	public CGP buscarCGPs(String calleOBarrio) {

		CentroDTO centroDTOEncontrado = serviceCGP.getCGPsByCalleOBarrio(calleOBarrio);

		return deCentroDTOaCGP(centroDTOEncontrado);

	}

	public CGP deCentroDTOaCGP(CentroDTO centroDTO) {

		CGP unCGP = new CGP(Integer.toString(centroDTO.getNumeroComuna()), centroDTO.getZonasIncluidas(),
				obtenerServiciosCGP(centroDTO));
		return unCGP;
	}

	public List<ServicioCGP> obtenerServiciosCGP(CentroDTO centroDTO) {

		List<ServicioCGP> serviciosCGP = centroDTO.getServiciosDTO().stream().map(servicioDTO -> this.deServicioDTOaServicioCGP(servicioDTO)).collect(Collectors.toList());
		return serviciosCGP;
	}
	private ServicioCGP deServicioDTOaServicioCGP(ServicioDTO servicioDTO) {

		ServicioCGP servicioCGP = new ServicioCGP(servicioDTO.getNombreServicio(),this.obtenerDiasDeAtencion(servicioDTO),
				this.aDisponibilidad(servicioDTO.getRangosDTO()));
		
		return servicioCGP;
	}

	public List<String> obtenerDiasDeAtencion(ServicioDTO servicioDTO) {
		List<Integer> numeroDias= servicioDTO.getRangosDTO().stream().map(rangoDTO -> rangoDTO.getNumeroSemana()).collect(Collectors.toList());
		return numeroDias.stream().map(numero -> convertirEnElDia(numero)).collect(Collectors.toList());
	}

	public String convertirEnElDia(int numero) {
		return DayOfWeek.of(numero).toString();
	}

	public Disponibilidad aDisponibilidad(List<RangoServicioDTO> rangos) {
		RangoServicioDTO rango = rangos.get(0);
		String horarioInicial = (mapearAStringHorario(rango.getHorarioDesde())) + ":"
				+ mapearAStringHorario((rango.getMinutosDesde()));
		String horarioCierre = (mapearAStringHorario(rango.getHorarioHasta())) + ":"
				+ mapearAStringHorario((rango.getMinutosHasta()));

		Disponibilidad unaDisponibilidadCGP = new Disponibilidad(horarioInicial, horarioCierre);

		return unaDisponibilidadCGP;
	}

	public String mapearAStringHorario(int intHorario) {
		if (intHorario < 10) {
			String stringHorario = "0" + Integer.toString(intHorario);

			return stringHorario;
		} else {
			return Integer.toString(intHorario);
		}
	}
	
	public void setServiceCGP(CGPService serviceCGP) {
		this.serviceCGP = serviceCGP;
	}

}
