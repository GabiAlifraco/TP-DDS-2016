package MocksServicios;

import DTOs.CentroDTO;
import seviciosExternos.CGPService;

public class MockedCGPService implements CGPService{

	private boolean consultado = false;
	

	@Override
	public CentroDTO getCGPsByCalleOBarrio(String calleOBarrio) {
		this.consultado = true;
		CentroDTO unCentroDTO = new CentroDTO();
		unCentroDTO.setDomicilioCompletoCGP(calleOBarrio+" 555");
		unCentroDTO.setZonasIncluidas(calleOBarrio);
		
		
		return unCentroDTO;
	}

	public boolean getConsultado() {
		return consultado;
	}
}
