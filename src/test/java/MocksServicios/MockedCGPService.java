package MocksServicios;

import DTOs.CentroDTO;
import seviciosExternos.CGPService;

public class MockedCGPService implements CGPService{

	@Override
	public CentroDTO getCGPsByCalleOBarrio(String calleOBarrio) {
		
		CentroDTO unCentroDTO = new CentroDTO();
		unCentroDTO.setDomicilioCompletoCGP(calleOBarrio+" 555");
		unCentroDTO.setZonasIncluidas(calleOBarrio);
		
		
		return unCentroDTO;
	}

}
