package Adapters;
import java.util.List;
import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import Pois.CGP;
import TpAnual.Disponibilidad;
import TpAnual.ServicioCGP;
import seviciosExternos.CGPService;
import java.util.stream.Collectors;
import java.time.DayOfWeek;


public class AdapterCGP {
	CGPService serviceCGP;

	public CGP buscarCGPs(String calleOBarrio) {

		CentroDTO centroDTOEncontrado = serviceCGP.getCGPsByCalleOBarrio(calleOBarrio);
		return this.deCentroDTOaCGP(centroDTOEncontrado);
	}

	public CGP deCentroDTOaCGP(CentroDTO centroDTO){
		List<ServicioCGP> serviciosCGP = this.obtenerServiciosCGP(centroDTO);
		List<String> palabrasClave = this.obtenerPalabrasClave(serviciosCGP);

		CGP unCGP = new CGP("Comuna "+(centroDTO.getNumeroComuna()),centroDTO.getZonasIncluidas(),serviciosCGP);
		unCGP.setLasPalabrasClave(palabrasClave);
		return unCGP;
	}


	private List<ServicioCGP> obtenerServiciosCGP(CentroDTO centroDTO) {
		List<ServicioCGP> serviciosCGP = centroDTO.getServiciosDTO().stream().map(servicioDTO -> this.deServicioDTOaServicioCGP(servicioDTO)).collect(Collectors.toList());
		return serviciosCGP;
	}
	private ServicioCGP deServicioDTOaServicioCGP(ServicioDTO servicioDTO) {
		List<String> diasAtencion= this.obtenerDiasDeAtencion(servicioDTO);
		Disponibilidad disponibilidadHoraria = this.aDisponibilidad(servicioDTO.getRangosDTO());

		ServicioCGP servicioCGP = new ServicioCGP(servicioDTO.getNombreServicio(),diasAtencion, disponibilidadHoraria);
		return servicioCGP;
	}
	public List<String> obtenerPalabrasClave(List<ServicioCGP> serviciosCGP){
		return serviciosCGP.stream().map(servicioCGP -> servicioCGP.getNombre()).collect(Collectors.toList());
	}
	
	public List<String> obtenerDiasDeAtencion(ServicioDTO servicioDTO) {
		List<Integer> numDias= servicioDTO.getRangosDTO().stream().map(rangoDTO -> rangoDTO.getNumeroSemana()).collect(Collectors.toList());
		return numDias.stream().map(numero -> DayOfWeek.of(numero)).map(day -> day.toString()).collect(Collectors.toList());
	}
	
	private Disponibilidad aDisponibilidad(List<RangoServicioDTO> rangosDTO) {
		RangoServicioDTO rango = rangosDTO.get(0);
		String horarioInicial = (rango.getHorarioDesde())+":"+(rango.getMinutosDesde());
		String horarioCierre = (rango.getHorarioHasta())+":"+(rango.getMinutosHasta());
		Disponibilidad unaDisponibilidadCGP= new Disponibilidad(horarioInicial,horarioCierre);
		return unaDisponibilidadCGP;
	}

	public void setServiceCGP(CGPService serviceCGP) {
		this.serviceCGP = serviceCGP;
	}
}

