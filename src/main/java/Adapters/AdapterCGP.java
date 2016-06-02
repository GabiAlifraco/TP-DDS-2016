package Adapters;

import java.util.ArrayList;
import java.util.List;
import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import Pois.CGP;
import TpAnual.Disponibilidad;
import TpAnual.OrigenDeDatos;
import TpAnual.Poi;
import TpAnual.ServicioCGP;
import seviciosExternos.CGPService;
import java.util.stream.Collectors;
import java.time.DayOfWeek;

public class AdapterCGP implements OrigenDeDatos {
	CGPService serviceCGP;

	public List<Poi> buscarPois(String unNombre, String unaPalabraClave) {

		List<Poi> listaCGPs = new ArrayList<Poi>();
		listaCGPs.addAll(buscarCGPs(unaPalabraClave));

		return listaCGPs;
	}

	public List<Poi> buscarCGPs(String calleOBarrio) {

		List<CentroDTO> centrosDTOEncontrados = serviceCGP.getCGPsByCalleOBarrio(calleOBarrio);

		return centrosDTOEncontrados.stream().map(centro -> deCentroDTOaCGP(centro)).collect(Collectors.toList());

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

	public ServicioCGP deServicioDTOaServicioCGP(ServicioDTO servicioDTO) {

		ServicioCGP servicioCGP = new ServicioCGP(servicioDTO.getNombreServicio(), obtenerDiasDeAtencion(servicioDTO),
				aDisponibilidad(servicioDTO.getRangosDTO()));
		return servicioCGP;
	}

	public List<String> obtenerDiasDeAtencion(ServicioDTO servicioDTO) {

		List<Integer> numeroDias = servicioDTO.getRangosDTO().getNumeroSemana();
		return numeroDias.stream().map(numero -> convertirEnElDia(numero)).collect(Collectors.toList());
	}

	public String convertirEnElDia(int numero) {
		return DayOfWeek.of(numero).toString();
	}

	public Disponibilidad aDisponibilidad(RangoServicioDTO rango) {

		String horarioInicial = (completarHora(rango.getHorarioDesde())) + ":"
				+ completarMinutos((rango.getMinutosDesde()));
		String horarioCierre = (completarHora(rango.getHorarioHasta())) + ":"
				+ completarMinutos((rango.getMinutosHasta()));

		Disponibilidad unaDisponibilidadCGP = new Disponibilidad(horarioInicial, horarioCierre);

		return unaDisponibilidadCGP;
	}

	public String completarHora(int hora) {
		if (hora < 10) {
			String nuevaHora = "0" + Integer.toString(hora);

			return nuevaHora;
		} else {
			return Integer.toString(hora);
		}

	}

	public String completarMinutos(int minutos) {
		if (minutos < 10) {
			String nuevosMinutos = "0" + Integer.toString(minutos);

			return nuevosMinutos;
		} else {
			return Integer.toString(minutos);
		}

	}

	public void setServiceCGP(CGPService serviceCGP) {
		this.serviceCGP = serviceCGP;
	}

}
