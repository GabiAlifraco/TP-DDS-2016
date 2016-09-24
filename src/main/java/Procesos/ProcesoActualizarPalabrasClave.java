package Procesos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import OrigenesDeDatos.Mapa;
import Pois.Poi;
import PoliticasReejecucion.Politica;

public class ProcesoActualizarPalabrasClave extends Proceso {
	
	public ProcesoActualizarPalabrasClave(Path archivo, Politica politica, Almacenador almacenador){
		this.archivo = archivo;
		this.politicaFallo = politica;
		this.almacenadorResultados = almacenador;
	}

	private Mapa base= Mapa.getInstance();
	private Path archivo;
	
	
	
	public void ejecutarProceso() {
			try {
				this.leerPalabrasAModificar(archivo);
			} catch (IOException e) {
				
			}
	}
	public void leerPalabrasAModificar(Path archivo) throws IOException{
		Files.lines(archivo).forEachOrdered(linea -> modificarPalabrasClave(linea));
	}

	private void modificarPalabrasClave(String linea) {
		String nombre = linea.split("[;]")[0];
		List<String> nuevasPClaves = Arrays.asList(linea.split("[;]")[1].split("[ ]"));
		List<Poi> poisAModificar = base.getPois().stream().filter(poi -> poi.mismoNombre(nombre)).collect(Collectors.toList());
		
		for(Poi poi: poisAModificar){
			poi.setPalabrasClave(nuevasPClaves);
			cantidadElementosAfectados++;
		}
	}

}