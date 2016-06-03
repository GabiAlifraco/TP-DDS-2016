package dds.TpAnual;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Observers.ObserverBusqueda;
import Observers.ObserverDemoraBusqueda;
import TpAnual.InfoFast;
import TpAnual.Resultado;

public class TestMandarMailAlAdministrador {
	
	private InfoFast sistema;
	private Resultado resultado;
	private LocalDate fecha;
	private List<ObserverBusqueda> observers = new ArrayList<ObserverBusqueda>();
	private ObserverDemoraBusqueda observerDemora;
	@Before
	public void initialize(){
		sistema = new InfoFast();
		fecha = LocalDate.parse("2016-10-16");
		resultado = new Resultado(fecha, 10, "sarasa", 4);
		observerDemora = new ObserverDemoraBusqueda();
		observers.add(observerDemora);
		observerDemora.setTiempoMaximoBusqueda(5);
		observerDemora.setSistema(sistema);
		observerDemora.setResultado(resultado);
	}
	@Test
	public void seLeNotificaraAlAdministrador(){
		observerDemora.notificarBusqueda();
	}

}
