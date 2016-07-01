package OrigenesDeDatos;

import java.util.List;

import Pois.Poi;

public interface OrigenDeDatos {
	public List<Poi> buscarPois(String unNombre, String unaPalabraClave);
	}