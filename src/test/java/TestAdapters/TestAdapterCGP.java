package TestAdapters;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import Adapters.AdapterCGP;
import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import Pois.CGP;

public class TestAdapterCGP {
	
	AdapterCGP adapterCGP = new AdapterCGP();

	private List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
	private List<RangoServicioDTO> rangosAtencionCiudadana = new ArrayList<RangoServicioDTO>();
	private List<RangoServicioDTO> rangosInfracciones = new ArrayList<RangoServicioDTO>();
	
	private ServicioDTO atencionCiudadana = new ServicioDTO();
	private ServicioDTO infracciones = new ServicioDTO();
	private RangoServicioDTO rangoAtencionCiudadana = new RangoServicioDTO();
	private RangoServicioDTO rangoInfracciones = new RangoServicioDTO();
	private CentroDTO centroDTO = new CentroDTO();
	private CGP cgpMapeado;
	
	@Before
	public void initialize() {

		centroDTO.setNumeroComuna(3);
		centroDTO.setZonasIncluidas("Balvanera, San Cristóbal");
		centroDTO.setDirector("Christian Etchezur");
		centroDTO.setDomicilioCompletoCGP("Junín 521");
		centroDTO.setTelefonoCGP("4375-0644/45");

		List<Integer> numeroDiasAtencionCiudadana = new ArrayList<Integer>();
		numeroDiasAtencionCiudadana.add(1);
		numeroDiasAtencionCiudadana.add(3);
		rangoAtencionCiudadana.setNumeroSemana(numeroDiasAtencionCiudadana);
		rangoAtencionCiudadana.setHorarioDesde(9);
		rangoAtencionCiudadana.setMinutosDesde(0);
		rangoAtencionCiudadana.setHorarioHasta(15);
		rangoAtencionCiudadana.setMinutosHasta(0);
		rangosAtencionCiudadana.add(rangoAtencionCiudadana);
		
		atencionCiudadana.setNombreServicio("Atención ciudadana");
		atencionCiudadana.setRangosDTO(rangoAtencionCiudadana);
	
		List<Integer> numeroDiasInfracciones = new ArrayList<Integer>();
		numeroDiasInfracciones.add(1);
		numeroDiasInfracciones.add(3);
		rangoInfracciones.setNumeroSemana(numeroDiasInfracciones);
		rangoInfracciones.setHorarioDesde(10);
		rangoInfracciones.setMinutosDesde(20);
		rangoInfracciones.setHorarioHasta(9);
		rangoInfracciones.setMinutosHasta(5);
		rangosInfracciones.add(rangoInfracciones);
		
		infracciones.setNombreServicio("Infracciones");
		infracciones.setRangosDTO(rangoInfracciones);

		serviciosDTO.add(infracciones);
		serviciosDTO.add(atencionCiudadana);
		
		centroDTO.setServiciosDTO(serviciosDTO);

	}
	
	@Test
	public void testCentroDTOMapeadoaCGP(){
		
		cgpMapeado = adapterCGP.deCentroDTOaCGP(centroDTO);
		Assert.assertTrue(cgpMapeado.getNombre().equals("CGP Comuna 3"));
		Assert.assertTrue(cgpMapeado.getRegion().getBarrio().equals("Balvanera, San Cristóbal"));
		Assert.assertTrue(cgpMapeado.getServicios().get(0).getNombre().equals("Infracciones"));
		Assert.assertTrue(cgpMapeado.getServicios().get(1).getNombre().equals("Atención ciudadana"));
		Assert.assertTrue(cgpMapeado.getServicios().size() == 2);
	}
	
}
