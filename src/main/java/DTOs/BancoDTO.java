package DTOs;

import java.util.ArrayList;
import java.util.List;

public class BancoDTO {
	private String banco;
	private String x;
	private String y;
	private List<String> servicios = new ArrayList<String>();
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
	public List<String> getServicios() {
		return servicios;
	}
	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

}
