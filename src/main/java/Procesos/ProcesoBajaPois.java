package Procesos;

import java.util.List;

import org.joda.time.LocalDateTime;

import OrigenesDeDatos.Mapa;
import Pois.Poi;
import PoliticasReejecucion.Politica;
import seviciosExternos.ServicioBajas;

public class ProcesoBajaPois extends Proceso {

	private ServicioBajas servicioRESTBajas;
	Mapa mapa = Mapa.getInstance();
	List<Poi> poisADarDeBaja;

	public ProcesoBajaPois(LocalDateTime cronograma, ServicioBajas servicioBajas, Politica politica, Almacenador almacenador) {
		this.cronograma = cronograma;
		this.servicioRESTBajas = servicioBajas;
		this.politicaFallo = politica;
		this.almacenadorResultados = almacenador;

	}

	public void ejecutarProceso(){
	
		poisADarDeBaja = servicioRESTBajas.consultarBajas();
		cantidadElementosAfectados = poisADarDeBaja.size();
		poisADarDeBaja.stream().forEach(poi -> mapa.eliminarUnPoi(poi));
		
	}
	
}