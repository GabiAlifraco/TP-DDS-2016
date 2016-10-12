package CaracteristicaPoi;

import javax.persistence.*;
@Entity
@Table(name="Region")
public class Region {
	@Id
	@GeneratedValue
	private Long RegionID;

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
		return RegionID;
	}
	public void setRegionID(long regionID) {
		RegionID = regionID;
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
