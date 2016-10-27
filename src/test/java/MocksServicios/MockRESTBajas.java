package MocksServicios;

import java.util.Arrays;
import java.util.List;

import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Ubicacion;
import Pois.Banco;
import Pois.CGP;
import Pois.Poi;
import seviciosExternos.ServicioBajas;

public class MockRESTBajas implements ServicioBajas{
	public Banco bancoSantander;
	private CGP cgpComuna3;
	private Punto coordenadaBanco = new Punto(34.3243,21.4484);
	private Ubicacion ubicacion = new Ubicacion(null, null, coordenadaBanco);

	
	@Override
	public List<Poi> consultarBajas() {
		bancoSantander = new Banco(null, null,ubicacion);
		cgpComuna3 = new CGP("3", null, null, null);
		
		return Arrays.asList(bancoSantander, cgpComuna3);
	}

	}
