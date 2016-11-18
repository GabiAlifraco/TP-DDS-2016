package controllers;

import java.time.LocalDate;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HistorialController extends Controller {

	public ModelAndView mostrarHistorialFiltroFecha(Request request, Response response){
		
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "admHistorial.hbs"));
	}
	
	public ModelAndView mostrarHistorialFiltroResultados(Request request, Response response){
		
			
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "admHistorial.hbs"));
	}
	
	public ModelAndView mostrarHistorialFiltroTerminal(Request request, Response response){
		
		
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "admHistorial.hbs"));
	}
}
