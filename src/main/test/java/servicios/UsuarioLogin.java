package servicios;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import modelo.datos.transferencia.DataRegister;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by Demils on 23/05/2016.
 */
public class UsuarioLogin extends DatabaseTest {

    @Test
    public void login_datosIncorrectos_respusta401(){
        //PRE
        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("o2480");

        //WHEN
        Response response = WebClient.create(URI_APP_BASE + "usuarios/login").post(loginData);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void login_datosCorrctos_respuesta200(){
        //PRE
        DataRegister registerData = new Usuario();
        registerData.setUsername("test");
        registerData.setEmail("test@test.com");
        registerData.setUbicacion("madrid");
        registerData.setPassword("139123");
        WebClient.create(URI_APP_BASE + "register").post(registerData);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");

        //WHEN
        Response response = WebClient.create(URI_APP_BASE + "usuarios/login").post(loginData);

        //ESPERADO
        Token token = response.readEntity(Token.class);
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
        assertThat(token, not(nullValue()));
    }
}
