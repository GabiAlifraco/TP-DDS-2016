package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class Controller {

	public ModelAndView redirigirSegunPermisos(Request request, Response response, String role, ModelAndView model) {
		if (request.cookies().containsKey("role") && request.cookie("role").toLowerCase().equals(role)) {
			return model;
		} else {
			response.status(401);
			response.redirect("/access-denied");
			return null;
		}

	}
}
