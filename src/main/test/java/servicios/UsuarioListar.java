package servicios;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListUser;
import modelo.datos.transferencia.DataLogin;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.*;

/**
 * Created by David on 02/06/2016.
 */
public class UsuarioListar extends DatabaseTest{

    @Test
    public void listarUser_noIdentificado_response401(){
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
        usuarioEnDatabase.setUsername("DSH");
        usuarioEnDatabase.setEmail("dsh@test.com");
        usuarioEnDatabase.setUbicacion("olae");
        usuarioEnDatabase.setPassword("hieveryone");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("CD");
        usuarioEnDatabase.setEmail("demils@test.com");
        usuarioEnDatabase.setUbicacion("olae");
        usuarioEnDatabase.setPassword("test");

        //WHEN
        Response response = WebClient.create(URI_APP_LISTAR_USUARIOS).get();
        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));

    }

    @Test
    public void listarUser_success_response200(){
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
        usuarioEnDatabase.setUsername("DSH");
        usuarioEnDatabase.setEmail("dsh@test.com");
        usuarioEnDatabase.setUbicacion("olae");
        usuarioEnDatabase.setPassword("hieveryone");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("CD");
        usuarioEnDatabase.setEmail("demils@test.com");
        usuarioEnDatabase.setUbicacion("olae");
        usuarioEnDatabase.setPassword("test");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);


        //WHEN
        Response response = WebClient.create(URI_APP_LISTAR_USUARIOS).header("Authentication", token.getToken()).get();
        DataListUser[] miLista = response.readEntity(DataListUser[].class);
        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
        assertThat(miLista.length, is(2));


    }

}
