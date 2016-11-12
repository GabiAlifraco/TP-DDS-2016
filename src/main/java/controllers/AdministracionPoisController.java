package controllers;

import OrigenesDeDatos.Mapa;
import Pois.Poi;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionPoisController {
	
	 public ModelAndView mostrar(Request request, Response response) {
		    return new ModelAndView(null, "admPois.hbs");
		  }

	 public Void eliminar (Request request, Response response){
		 
		 Mapa miMapa= Mapa.getInstance();
		 
		 Poi miPoi=miMapa.buscarPois(request.queryParams("nombrePoi"), "").get(0);
		
		 miMapa.eliminarUnPoi(miPoi);
		 
		 response.redirect("/administracionPois");
		 
		 return null;
	 }
	 
}
