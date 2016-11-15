package controllers;

import org.apache.commons.codec.digest.DigestUtils;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class Controller {

	public ModelAndView redirigirSegunPermisos(Request request, Response response, String role, ModelAndView model) {
		if (request.cookies().containsKey("role") && request.cookie("role").equals(DigestUtils.sha1Hex(role))) {
			return model;
		} else {
			response.status(401);
			return new ModelAndView(null, "access-denied.hbs");
			//return model;  Para probar las pantallas comento codigo anterior y muestro pantalla asi no me restringe el login
		}

	}
}
