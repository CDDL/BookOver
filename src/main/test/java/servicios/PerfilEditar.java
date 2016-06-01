package servicios;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by Demils on 31/05/2016.
 */
public class PerfilEditar extends DatabaseTest {

    @Test
    public void editarPerfil_edicionSuccess_respuesta200() {
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_BASE + "register").post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_BASE + "login").post(loginData);
        Token token = loginResponse.readEntity(Token.class);

        //WHEN
        Usuario datosEditados = new Usuario();
        datosEditados.setPassword("123456");
        datosEditados.setEmail("email@valido.com");
        datosEditados.setUbicacion("castellón");
        Response response = WebClient.create(URI_APP_BASE + "perfil/editar").header("Authentication", token.getToken()).post(datosEditados);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void editarPerfil_usuarioNoIdentificado_respuesta401() {
        //PRE

        //WHEN
        Usuario datosEditados = new Usuario();
        datosEditados.setPassword("123456");
        datosEditados.setEmail("email@valido.com");
        datosEditados.setUbicacion("castellón");
        Response response = WebClient.create(URI_APP_BASE + "perfil/editar").post(datosEditados);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

}
