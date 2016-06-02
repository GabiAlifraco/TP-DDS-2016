package seviciosExternos;

import java.util.List;

import DTOs.CentroDTO;

public interface CGPService {

	public List<CentroDTO> getCGPsByCalleOBarrio(String calleOBarrio);
}
