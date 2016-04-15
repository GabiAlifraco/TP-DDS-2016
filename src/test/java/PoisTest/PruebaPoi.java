package PoisTest;

import org.junit.Before;
import org.junit.Test;
import TpAnual.Pois;

public class PruebaPoi {
private Pois l114ParadaCampus;
private Pois l114ParadaEscalada;
private double distanciaLatitud;
private double distanciaLongitud;
private double distancia;

@Before
public void initialize(){
	l114ParadaCampus = new Pois(-34.66,-58.47,"114-ParadaCampus","Mozart",2300,"Colectivo ");
	l114ParadaEscalada = new Pois(-30.26,-55.22,"114-ParadaEscalada","Escalada",1500,"Colectivo ");
}

@Test
public void mostrarPoi(){
distanciaLatitud= distance(l114ParadaEscalada.getLatitud(),l114ParadaCampus.getLatitud());
distanciaLongitud = distance(l114ParadaEscalada.getLongitud(),l114ParadaCampus.getLongitud());
distancia = Math.sqrt(Math.pow(distanciaLatitud,2)+ Math.pow(distanciaLongitud,2));
System.out.print(l114ParadaEscalada.getNombre()+" de " + l114ParadaCampus.getNombre()+ " esta a una distancia de :" + distancia);
}

}
