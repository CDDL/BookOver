package servicios;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListUser;
import modelo.datos.transferencia.DataLogin;
import modelo.datos.transferencia.DataMensaje;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.*;

/**
 * Created by David on 02/06/2016.
 */

public class ConversacionListar extends DatabaseTest {

    @Test
    public void listarConversaciones_noIdentificado_response401(){
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(loginData);
        Token token = loginResponse.readEntity(Token.class);

        usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("cd");
        usuarioEnDatabase.setEmail("demils@test.com");
        usuarioEnDatabase.setUbicacion("olae");
        usuarioEnDatabase.setPassword("test");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("david");
        usuarioEnDatabase.setEmail("david@test.com");
        usuarioEnDatabase.setUbicacion("cs");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);


        Response listResponse = WebClient.create(URI_APP_LISTAR_USUARIOS).header("Authentication", token.getToken()).get();
        DataListUser[] miLista = listResponse.readEntity(DataListUser[].class);
        int idusuario = miLista[0].getId();

        DataMensaje mensaje = new DataMensaje();
        mensaje.setMensaje("Hola gente como van esos tests?");
        mensaje.setPara(idusuario);
        WebClient.create(URI_APP_ENVIAR_MENSAJE).header("Authentication", token.getToken()).post(mensaje);

        idusuario = miLista[1].getId();
        mensaje = new DataMensaje();
        mensaje.setMensaje("Hola gente como van esos tests?");
        mensaje.setPara(idusuario);
        WebClient.create(URI_APP_ENVIAR_MENSAJE).header("Authentication", token.getToken()).post(mensaje);

        //WHEN
        Response response = WebClient.create(URI_APP_LISTAR_CONVERSACIONES).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void listarConversaciones_success_response200(){
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(loginData);
        Token token = loginResponse.readEntity(Token.class);

        usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("cd");
        usuarioEnDatabase.setEmail("demils@test.com");
        usuarioEnDatabase.setUbicacion("olae");
        usuarioEnDatabase.setPassword("test");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("david");
        usuarioEnDatabase.setEmail("david@test.com");
        usuarioEnDatabase.setUbicacion("cs");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);


        Response listResponse = WebClient.create(URI_APP_LISTAR_USUARIOS).header("Authentication", token.getToken()).get();
        DataListUser[] miLista = listResponse.readEntity(DataListUser[].class);
        int idusuario = miLista[0].getId();

        DataMensaje mensaje = new DataMensaje();
        mensaje.setMensaje("Hola gente como van esos tests?");
        mensaje.setPara(idusuario);
        WebClient.create(URI_APP_ENVIAR_MENSAJE).header("Authentication", token.getToken()).post(mensaje);

        idusuario = miLista[1].getId();
        mensaje = new DataMensaje();
        mensaje.setMensaje("Hola gente como van esos tests?");
        mensaje.setPara(idusuario);
        WebClient.create(URI_APP_ENVIAR_MENSAJE).header("Authentication", token.getToken()).post(mensaje);

        //WHEN
        Response response = WebClient.create(URI_APP_LISTAR_CONVERSACIONES).header("Authentication", token.getToken()).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }
}
