package main;

import static spark.Spark.*;


import controllers.AccesosController;
import controllers.AdministracionPoisController;
import controllers.HistorialController;
import controllers.HomeController;
import controllers.TerminalController;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
	public static void main(String[] args) {
		
		new Bootstrap().run();
		System.out.println("Iniciando servidor");
		staticFileLocation("/public");
		TerminalController terminal= new TerminalController();
		HomeController home = new HomeController();
		AccesosController accesos = new AccesosController();
		AdministracionPoisController administracion= new AdministracionPoisController();
		HistorialController historial = new HistorialController();
		DebugScreen.enableDebugScreen();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		//PERFIL ADMINISTRADOR  USER:admin  PASS: admin
		
		port(8080);


		get("/", home::mostrar, engine); //Pagina Principal
		get("/login", accesos::mostrarLogin, engine); //Se muestra cuando presiono en el boton de la pagina principal
		post("/login", accesos::autenticar); //Sucede cuando presionamos iniciar sesion luego de ingresar los datos
		get("/loginfailed", accesos::mostrarLoginFail, engine); //En caso que haya un error en los datos
		post("/administrador", accesos::cerrarSesion); //Si presiono cerrar sesion durante el uso de la aplicacion
		get("/access-denied", accesos::denegarAcceso, engine); //Al querer ingresar en alguna pagina de la app sin estar logueado
		post("/administrador/*", accesos::cerrarSesion);
		post("/logout", accesos::cerrarSesion);

		get("/administrador", home::mostrarHomeAdmin, engine);//Si entro como perfil administrador
		
		get("/administrador/pois",administracion::mostrarPois,engine); //Muestro Pois
		get("/administrador/pois/:id", administracion::eliminar, engine); //Elimino un Poi
		get("/administrador/pois/modificar/:id", administracion::modificarPoi, engine); //Modifico un Poi
		get("/administrador/pois/modificar/guardar/:id", administracion::actualizarPoi, engine); //Guardo Modificacion
		
		get("/administrador/terminales/agregar", administracion::nueva, engine); //Agrego nueva terminal
		get("/administrador/terminales/nueva/agregar", administracion::agregarTerminal, engine);
		get("/administrador/terminales",administracion::mostrarTerminales,engine);//Muestro terminales
		get("/administrador/terminales/:id", administracion::eliminarTerminal,engine);
		get("/administrador/terminales/modificar/:id", administracion::modificarTerminal,engine);
		get("/administrador/terminales/modificar/guardar/:id", administracion::actualizarTerminal,engine);
		
		
		get("/administrador/historial",administracion::mostrarHistorial,engine);//Muestro historial de Consultas
		post("/administrador/historial/filtroFecha",historial::mostrarHistorialFiltroFecha,engine);
		post("/administrador/historial/filtroResultados",historial::mostrarHistorialFiltroResultados,engine);
		post("/administrador/historial/filtroTerminal",historial::mostrarHistorialFiltroTerminal,engine);

		get("/terminal",home::mostrarHomeTerminal,engine);//Si entro como perfil terminal
		get("/terminal/bancos",terminal::mostrarBancos,engine);
		get("/terminal/cgps",terminal::mostrarCgps,engine);
		get("/terminal/paradas",terminal::mostrarParadas,engine);
		get("/terminal/locales",terminal::mostrarLocales,engine);
		get("/terminal/detalle/:id",terminal::mostrarDetalle,engine); //Veo el detalle del Poi
	}
}