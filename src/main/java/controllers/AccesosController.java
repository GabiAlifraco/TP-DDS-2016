package controllers;

import org.apache.commons.codec.digest.DigestUtils;

import Accesos.AdministradorUsuarios;
import Accesos.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AccesosController {
	public ModelAndView mostrarLogin(Request request, Response response) {
		return new ModelAndView(null, "login.hbs");
	}

	public ModelAndView mostrarLoginFail(Request request, Response response) {
		return new ModelAndView(null, "loginfailed.hbs");
	}

	public Void autenticar(Request request, Response response) {
		AdministradorUsuarios adminUsr = new AdministradorUsuarios();
		String user = request.queryParams("user");
		String pass = request.queryParams("password");

		Usuario usuarioBase = adminUsr.buscarUsuario(user);
		String requestPasswordHash = DigestUtils.sha1Hex(pass);

		response.cookie("role", usuarioBase.getRole());
		if (usuarioBase != null && requestPasswordHash.equals(usuarioBase.getPassword())) {
			String rol = usuarioBase.getRole().toLowerCase(); 
			response.redirect(rol); 
		} else {
			response.redirect("/loginfailed");
		}
		return null;
	}
	public ModelAndView denegarAcceso(Request request, Response response) {
		return new ModelAndView(null, "access-denied.hbs");
	}
	public Void cerrarSesion(Request request, Response response){
		response.removeCookie("role");
		response.redirect("/");
		return null;
	}
}