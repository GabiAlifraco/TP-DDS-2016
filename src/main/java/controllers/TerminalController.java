package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


import Pois.Poi;
import Terminal.AdministradorTerminales;
import Terminal.Terminal;
import main.listaPois;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController extends Controller {
	private listaPois clista = new listaPois();
	private List<Poi> pois=new ArrayList<Poi>();
	public ModelAndView mostrarHomeTerminal(Request request, Response response) {
		return this.redirigirSegunPermisos(request, response,"terminal", new ModelAndView(null, "home-terminal.hbs"));
	}
	public ModelAndView buscar(Request request, Response response) {
		pois =clista.listar();
		//AdministradorTerminales admTerminales = new AdministradorTerminales();
		String nombre = request.queryParams("nombre2");
		String pclave = request.queryParams("palabrasClave");
		String tipo =request.queryParams("tipo");
		HashMap<String, Object> viewModel = new HashMap<>();
		//String nombreTerminal= request.cookie("nombreTerminal");
		List<Poi> poisaux=new ArrayList<Poi>();
		//Terminal terminal = admTerminales.listar().stream().filter(t->t.getNombreTerminal().equals(nombreTerminal)).collect(Collectors.toList()).get(0);
		if(tipo.equals("tipo")){
			if(pclave.equals("palabra clave")){
				//pois=terminal.getBase().buscarPois(nombre,nombre);
				
				poisaux=pois.stream().filter(p -> p.textoIncluido(nombre, nombre)).collect(Collectors.toList());
				viewModel.put("hasResults", !poisaux.isEmpty());
				viewModel.put("results", this.convertPois(poisaux));
				
			}
			//pois = terminal.getBase().buscarPois(nombre, pclave);
			poisaux=pois.stream().filter(p -> p.textoIncluido(nombre, pclave)).collect(Collectors.toList());
			viewModel.put("hasResults", !poisaux.isEmpty());
			viewModel.put("results", this.convertPois(poisaux));
			
			}
		poisaux= pois.stream().filter(p -> p.dValue.equalsIgnoreCase(tipo)).collect(Collectors.toList());
		poisaux= pois.stream().filter(p -> p.textoIncluido(nombre, pclave)).collect(Collectors.toList());
		viewModel.put("hasResults", !poisaux.isEmpty());
		viewModel.put("results", this.convertPois(poisaux));
		
		
		return this.redirigirSegunPermisos(request, response, "terminal", new ModelAndView(viewModel, "mapa-pois.hbs"));
		
	}
	private List<HashMap<String, Object>> convertPois(List<Poi> pois2) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();
		for ( Poi p : pois2){
			HashMap<String,Object> element = new HashMap<String,Object>();
			element.put("nombre",p.getNombre());
			StringBuilder sb2 = new StringBuilder();
			sb2.append(p.getUbicacion().getDomicilio().getCallePrincipal()).append(" ").append(p.getUbicacion().getDomicilio().getAlturaPrincipal())
			.append(", ").append(p.getRegion().getBarrio()).append(",CABA");
			element.put("direccion",sb2.toString() );
			StringBuilder sb3 = new StringBuilder();
			sb3.append(" Piso:").append(String.valueOf(p.getUbicacion().getDomicilio().getPiso())).append("Depto:").append(p.getUbicacion().getDomicilio().getUnidad());
			element.put("edificio",sb3.toString());
			array.add(element);
		}
		return array;
	}
}
