package seviciosExternos;

import java.util.List;

import DTOs.BancoDTO;

public interface BankService {

	public List<BancoDTO> getSucursalesBancosByNombreBanco(String nombreBanco, String nombreServicio);
}
