package Adapters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import DTOs.CentroDTO;
import Pois.CGP;
import json.JsonFactory;
import seviciosExternos.CGPService;

public class AdapterCGP {

	CGPService servicioCGP;
	JsonFactory jsonFactory = new JsonFactory();
	
	public List<CGP> buscarCGPs(String calleOBarrio){
		
		CentroDTO centroDTO = servicioCGP.getCGPsByCalleOBarrio(calleOBarrio);
		List<CentroDTO> cgpsEncontrados = Arrays.asList(centroDTO);
		
		return cgpsEncontrados.stream()
				.map(cgpEncontrado -> mapearCentroDTOACGP(cgpEncontrado))
				.collect(Collectors.toList());
	}

	private CGP mapearCentroDTOACGP(CentroDTO cgpEncontrado) {
		
		return null;
	}
}
