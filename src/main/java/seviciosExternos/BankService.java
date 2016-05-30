package seviciosExternos;

import java.net.URL;

public interface BankService {

	public URL getSucursalesBancosByNombreBanco(String nombreBanco, String nombreServicio);
}
