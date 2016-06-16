package CaracteristicaPoi;

public class Region {
	
	private String localidad;
	private String barrio;
	private String provincia;
	private String pais;
	
	public Region(String unaLocalidad,String unBarrio,String unaProvincia,String unPais){
		this.localidad = unaLocalidad;
		this.barrio = unBarrio;
		this.provincia = unaProvincia;
		this.pais = unPais;
	}
    //Getters
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
}
