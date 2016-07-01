package Procesos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import OrigenesDeDatos.Mapa;

public class ProcesoActualizarPalabrasClave extends Proceso {

	private Mapa base= Mapa.getInstance();
	private Path archivoConActualizaciones;
	
	public ProcesoActualizarPalabrasClave(Path archivo){
		archivoConActualizaciones= archivo;
	}
	@Override
	public boolean ejecutar() {
		try {
			this.leerPalabrasAModificar(archivoConActualizaciones);
		} catch (IOException e) {
			finalizoOK=false;
		}
		return finalizoOK;
	}

	private void leerPalabrasAModificar(Path archivo) throws IOException{
		Files.lines(archivo).forEachOrdered(linea -> modificarPalabrasClave(linea));
	}

	private void modificarPalabrasClave(String linea) {
		String nombre = linea.split("[;]")[0];
		List<String> nuevasPClaves = Arrays.asList(linea.split("[;]")[1].split("[ ]"));
		base.getPois().stream().filter(poi -> poi.mismoNombre(nombre)).forEach(poi -> poi.setPalabrasClave(nuevasPClaves));
	}

	
}
