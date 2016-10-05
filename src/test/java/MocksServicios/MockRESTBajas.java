package MocksServicios;

import java.util.Arrays;
import java.util.List;
import org.uqbar.geodds.Point;
import Pois.Banco;
import Pois.CGP;
import Pois.Poi;
import seviciosExternos.ServicioBajas;

public class MockRESTBajas implements ServicioBajas{
	public Banco bancoSantander;
	private CGP cgpComuna3;
	private Point coordenadaBanco = new Point(34.3243,21.4484);
	

	
	@Override
	public List<Poi> consultarBajas() {
		bancoSantander = new Banco(null, coordenadaBanco, null,null);
		cgpComuna3 = new CGP("3", null, null, null);
		
		return Arrays.asList(bancoSantander, cgpComuna3);
	}

	}
