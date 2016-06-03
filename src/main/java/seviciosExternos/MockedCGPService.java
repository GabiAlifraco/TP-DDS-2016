package seviciosExternos;

import DTOs.CentroDTO;

public class MockedCGPService implements CGPService{

	@Override
	public CentroDTO getCGPsByCalleOBarrio(String calleOBarrio) {
		
		CentroDTO unCentroDTO = new CentroDTO();
		unCentroDTO.setDomicilioCompletoCGP(calleOBarrio);
		unCentroDTO.setZonasIncluidas(calleOBarrio);
		
		
		return unCentroDTO;
	}

}
