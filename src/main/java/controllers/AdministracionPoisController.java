package controllers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionPoisController {
	
	 public ModelAndView mostrar(Request request, Response response) {
		    return new ModelAndView(null, "admPois.hbs");
		  }

}
