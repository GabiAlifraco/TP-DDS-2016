package controllers;

import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;

import Accesos.AdministradorUsuarios;
import Accesos.Usuario;
import Terminal.AdministradorTerminales;
import Terminal.Terminal;
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
		AdministradorTerminales terminales = new AdministradorTerminales();
		String user = request.queryParams("user");
		String pass = request.queryParams("password");

		Usuario usuarioBase = adminUsr.buscarUsuario(user);
		String requestPasswordHash = DigestUtils.sha1Hex(pass);
		
		if (usuarioBase != null && requestPasswordHash.equals(usuarioBase.getPassword())) {
		String rol = usuarioBase.getRole().toLowerCase(); 
		String hashRol = DigestUtils.sha1Hex(rol);
		if(rol.equals("terminal")){
			Terminal terminal=terminales.listar().stream().filter(t -> t.getUsuario().getUser().equals(usuarioBase.getUser())).collect(Collectors.toList()).get(0);
			response.cookie("nombreTerminal", terminal.getNombreTerminal());
		}
			response.cookie("role", hashRol);
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