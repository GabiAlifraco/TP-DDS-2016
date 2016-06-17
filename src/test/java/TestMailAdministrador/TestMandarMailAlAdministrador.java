package TestMailAdministrador;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Inicializacion.CreadorDeObjetos;

public class TestMandarMailAlAdministrador extends CreadorDeObjetos {
	
	@Before
	public void initialize(){
		this.crearMail();
	}
    @Test
	public void seLeNotificaraAlAdministrador(){
		
		Assert.assertTrue(notificadorAdministrador.administradorNotificado);
	}

}