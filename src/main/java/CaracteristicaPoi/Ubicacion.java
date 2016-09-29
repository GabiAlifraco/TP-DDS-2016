package CaracteristicaPoi;

import org.uqbar.geodds.Point;

import converter.PointConverter;

import javax.persistence.*;

@Entity
public class Ubicacion {
	@Id
	@GeneratedValue
	private long ubicacionID;
	@Convert(converter = PointConverter.class)
	private Point coordenadas;
	@OneToOne
	private Domicilio domicilio;
	@OneToOne
	private Region region;
	
	public Ubicacion(Domicilio domicilio, Region region, Point coordenadas){
		setDomicilio(domicilio);
		setRegion(region);
		setCoordenadas(coordenadas);
	}
	//Getters y Setters
	public Point getCoordenadas() {
		return coordenadas;
	}
    private void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}
    private void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Region getRegion() {
		return region;
	}
    private void setRegion(Region region) {
		this.region = region;
	}
}
