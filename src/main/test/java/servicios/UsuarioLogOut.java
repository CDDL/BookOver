package servicios;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import modelo.datos.transferencia.DataRegister;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static servicios.Config.URI_APP_BASE;
import static servicios.Config.URI_APP_LOGOUT;

/**
 * Created by Demils on 02/06/2016.
 */
public class UsuarioLogOut {
    @Test
    public void logOut_usuarioIdentificado_respuesta200(){
        //PRE
        DataRegister registerData = new Usuario();
        registerData.setUsername("test");
        registerData.setEmail("test@test.com");
        registerData.setUbicacion("madrid");
        registerData.setPassword("139123");
        WebClient.create(URI_APP_BASE + "usuarios").post(registerData);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        WebClient.create(URI_APP_BASE + "usuarios/login").post(loginData);

        //WHEN
        Response response = WebClient.create(URI_APP_LOGOUT).delete();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void logOut_usuarioNoIdentificado_respuesta200(){
        //PRE

        //WHEN
        Response response = WebClient.create(URI_APP_LOGOUT).delete();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }
}
