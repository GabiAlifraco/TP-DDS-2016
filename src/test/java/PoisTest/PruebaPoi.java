package PoisTest;

import org.junit.Before;
import org.junit.Test;
import TpAnual.Pois;

public class PruebaPoi {
private Pois linea114;

@Before
public void initialize(){
	linea114 = new Pois(-34.66,-58.47,"114-ParadaCampus","Mozart",2300,"Colectivo ");
}

@Test
public void mostrarPoi(){
	System.out.println(linea114.getNombre());
}

}
