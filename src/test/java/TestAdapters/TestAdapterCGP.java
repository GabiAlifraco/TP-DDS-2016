package TestAdapters;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import DTOs.CentroDTO;
import DTOs.RangoServicioDTO;
import DTOs.ServicioDTO;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.CGP;

public class TestAdapterCGP {
	
	ProveedorCGPs adapterCGP = new ProveedorCGPs();

	private List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
	
	private ServicioDTO atencionCiudadana = new ServicioDTO();
	private RangoServicioDTO rango1AtencionCiudadana = new RangoServicioDTO();
	private RangoServicioDTO rango2AtencionCiudadana = new RangoServicioDTO();
	private List<RangoServicioDTO> rangosAtencionCiudadana = new ArrayList<RangoServicioDTO>();
	
	private ServicioDTO infracciones = new ServicioDTO();
	private RangoServicioDTO rango1Infracciones = new RangoServicioDTO();
	private List<RangoServicioDTO> rangosInfracciones = new ArrayList<RangoServicioDTO>();

	private CentroDTO centroDTO = new CentroDTO();
	
	private CGP cgpMapeado;

	
	
	@Before
	public void initialize() {

		centroDTO.setNumeroComuna(3);
		centroDTO.setZonasIncluidas("Balvanera, San Cristóbal");
		centroDTO.setDirector("Christian Etchezur");
		centroDTO.setDomicilioCompletoCGP("Junín 521");
		centroDTO.setTelefonoCGP("4375-0644/45");

		
		rango1AtencionCiudadana.setNumeroSemana(1);
		rango1AtencionCiudadana.setHorarioDesde(9);
		rango1AtencionCiudadana.setMinutosDesde(0);
		rango1AtencionCiudadana.setHorarioHasta(15);
		rango2AtencionCiudadana.setMinutosHasta(0);
		
		rango2AtencionCiudadana.setNumeroSemana(2);
		rango2AtencionCiudadana.setHorarioDesde(10);
		rango2AtencionCiudadana.setMinutosDesde(0);
		rango2AtencionCiudadana.setHorarioHasta(18);
		rango2AtencionCiudadana.setMinutosHasta(0);
		
		rangosAtencionCiudadana.add(rango1AtencionCiudadana);
		rangosAtencionCiudadana.add(rango2AtencionCiudadana);
		
		atencionCiudadana.setRangosDTO(rangosAtencionCiudadana);
		
		atencionCiudadana.setNombreServicio("Atención ciudadana");
	
		rango1Infracciones.setNumeroSemana(1);
		rango1Infracciones.setHorarioDesde(10);
		rango1Infracciones.setMinutosDesde(20);
		rango1Infracciones.setHorarioHasta(9);
		rango1Infracciones.setMinutosHasta(5);
		rangosInfracciones.add(rango1Infracciones);
		
		infracciones.setRangosDTO(rangosInfracciones);
		infracciones.setNombreServicio("Infracciones");

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
