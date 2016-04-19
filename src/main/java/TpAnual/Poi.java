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
		
		this.callePrincipal = unaCallePrincipal;
		this.alturaPrincipal = unaAlturaPrincipal;
	    this.entreLaCalle = entreUnaCalle;
		this.hastaLaCalle = hastaOtraCalle;
		this.alturaCalles = unaAlturaCalles;
		this.piso = unPiso;
		this.departamento = unDepartamento;
		this.unidad = unaUnidad;
		this.codigoPostal = unCodigoPostal;
		this.localidad = unaLocalidad;
		this.barrio = unBarrio;
		this.provincia = unaProvincia;
		this.pais = unPais;
		this.latitud = unaLatitud;
		this.longitud = unaLongitud;
	
	}

	public int getAlturaPrincipal() {
		return alturaPrincipal;
	}

    public int getAlturaCalles() {
		return alturaCalles;
	}
    
    public double getLatitud(){
    	return latitud;
    }
    public double getLongitud(){
    	return longitud;
    }
    public String getCallePrincipal() {
		return callePrincipal;
	}
    public String getEntreLaCalle() {
		return entreLaCalle;
	}
    public String getHastaLaCalle() {
		return hastaLaCalle;
	}
    public int getPiso() {
		return piso;
	}
    public int getDepartamento() {
		return departamento;
	}
    public int getUnidad() {
		return unidad;
	}
    public int getCodigoPostal() {
		return codigoPostal;
	}
    public String getLocalidad() {
		return localidad;
	}
    public String getBarrio() {
		return barrio;
	}
    public String getProvincia() {
		return provincia;
	}
    public String getPais() {
		return pais;
	}
    
    public int diferenciaAlturas(int altura1,int altura2){
    	if(altura1>altura2){
    		return altura1-altura2;
    	}
    	else{
    		return altura2-altura1;
    	}
     }
    
    public int calculoDistanciaEntrePois(Poi unPoi){
    	return this.diferenciaAlturas(this.getAlturaPrincipal(), unPoi.getAlturaPrincipal()) +
    		   this.diferenciaAlturas(this.getAlturaCalles(), unPoi.getAlturaCalles());	
    }

	

	
	
}
