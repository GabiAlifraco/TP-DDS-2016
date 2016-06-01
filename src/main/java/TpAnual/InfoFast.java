package TpAnual;

import java.util.ArrayList;
import java.util.List;

public class InfoFast {

	private static InfoFast instance = null;
	List<Terminal> terminales = new ArrayList<Terminal>();
	private String mailAdministrador;

	public static InfoFast getInstance() {
		if (instance == null) {
			instance = new InfoFast();
		}
		return instance;
	}

	public void agregarTerminal(Terminal nuevaTerminal) {
		this.getTerminales().add(nuevaTerminal);
	}

	private List<Terminal> getTerminales() {
		return this.terminales;
	}

	public String getMailAdministrador() {
		return mailAdministrador;
	}

	public void setMailAdministrador(String mailAdministrador) {
		this.mailAdministrador = mailAdministrador;
	}
}
