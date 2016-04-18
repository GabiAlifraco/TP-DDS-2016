package TpAnual;

public class Poi {

	private String callePrincipal;
	private int alturaPrincipal;
	private String entreLaCalle;
	private String hastaLaCalle;
	private int alturaCalles;
	private int piso;
	private int departamento;
	private int unidad;
	private int codigoPostal;
	private String localidad;
	private String barrio;
	private String provincia;
	private String pais;
	private double latitud;
	private double longitud;
	
	public Poi(String unaCallePrincipal,
			   int unaAlturaPrincipal,
			   String entreUnaCalle,
			   String hastaOtraCalle,
			   int unaAlturaCalles,
			   int unPiso,
			   int unDepartamento,
			   int unaUnidad,
			   int unCodigoPostal,
			   String unaLocalidad,
			   String unBarrio,
			   String unaProvincia,
			   String unPais,
			   double unaLatitud,
			   double unaLongitud){
		
	this.setCallePrincipal(unaCallePrincipal);
	this.setAlturaPrincipal(unaAlturaPrincipal);
	this.setEntreLaCalle(entreUnaCalle);
	this.setHastaLaCalle(hastaOtraCalle);
	this.setAlturaCalles(unaAlturaCalles);
	this.setPiso(unPiso);
	this.setDepartamento(unDepartamento);
	this.setUnidad(unaUnidad);
	this.setCodigoPostal(unCodigoPostal);
	this.setLocalidad(unaLocalidad);
	this.setBarrio(unBarrio);
	this.setProvincia(unaProvincia);
	this.setPais(unPais);
	this.setLatitud(unaLatitud);
	this.setLongitud(unaLongitud);
	}
	public String getCallePrincipal() {
		return callePrincipal;
	}
	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}
	public int getAlturaPrincipal() {
		return alturaPrincipal;
	}
	public void setAlturaPrincipal(int alturaPrincipal) {
		this.alturaPrincipal = alturaPrincipal;
	}
	public String getEntreLaCalle() {
		return entreLaCalle;
	}
	public void setEntreLaCalle(String entreLaCalle) {
		this.entreLaCalle = entreLaCalle;
	}
	public String getHastaLaCalle() {
		return hastaLaCalle;
	}
	public void setHastaLaCalle(String hastaLaCalle) {
		this.hastaLaCalle = hastaLaCalle;
	}
	public int getAlturaCalles() {
		return alturaCalles;
	}
	public void setAlturaCalles(int alturaCalles) {
		this.alturaCalles = alturaCalles;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public int getUnidad() {
		return unidad;
	}
	public void setUnidad(int unaUnidad) {
		this.unidad = unaUnidad;
	}
	public int getDepartamento() {
		return departamento;
	}
	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	public int sumaAlturaPoi(){
		return this.getAlturaPrincipal() + this.getAlturaCalles();
	}
	
	public int calculoDifAlturaEntrePois(Poi unPoi){
		if(this.sumaAlturaPoi() > unPoi.sumaAlturaPoi()){
			return this.sumaAlturaPoi() - unPoi.sumaAlturaPoi();
		}
		else{
			return unPoi.sumaAlturaPoi() - this.sumaAlturaPoi();
		}
	}
}
