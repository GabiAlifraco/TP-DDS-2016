package controllers;

import org.apache.commons.codec.digest.DigestUtils;

import Accesos.AdministradorUsuarios;
import Accesos.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	public ModelAndView mostrar(Request request, Response response) {
		return new ModelAndView(null, "login.hbs");
	}

	public ModelAndView mostrarFail(Request request, Response response) {
		return new ModelAndView(null, "loginfailed.hbs");
	}

	public Void autenticar(Request request, Response response) {
		AdministradorUsuarios adminUsr = new AdministradorUsuarios();
		String user = request.queryParams("user");
		String pass = request.queryParams("password");

		Usuario usuarioBase = adminUsr.buscarUsuario(user);
		String requestPasswordHash = DigestUtils.sha1Hex(pass);

		if (usuarioBase != null && requestPasswordHash.equals(usuarioBase.getPassword())) {
			String rol = usuarioBase.getRole().toLowerCase(); 
			response.redirect("/home-"+ rol); 
		} else {
			response.redirect("/loginfailed");
		}
		return null;
	}
	
}