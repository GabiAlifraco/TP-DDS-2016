package CaracteristicaPoi;

@javax.persistence.Entity
@javax.persistence.Table(name="Regiones")
public class Region {
	
	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	private Long regionID;

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
	public Region(){
		
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
	public long getRegionID() {
		return regionID;
	}
	public void setRegionID(long regionID) {
		regionID = regionID;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
}
