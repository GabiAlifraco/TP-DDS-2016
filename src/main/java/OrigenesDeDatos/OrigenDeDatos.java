package OrigenesDeDatos;

import java.util.List;

import CaracteristicaPoi.Poi;



public interface OrigenDeDatos {

	public List<Poi> buscarPois(String unNombre, String unaPalabraClave);
	}
