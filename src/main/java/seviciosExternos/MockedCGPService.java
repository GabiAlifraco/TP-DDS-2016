package seviciosExternos;

import DTOs.CentroDTO;

public class MockedCGPService implements CGPService{

	@Override
	public CentroDTO getCGPsByCalleOBarrio(String calleOBarrio) {
		
		return new CentroDTO();
	}

}
