package servicios;

import modelo.datos.transferencia.DataListUser;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.URI_APP_LISTAR_USUARIOS;
import static servicios.Config.URI_APP_VISUALIZAR_CONVERSACION;
import static servicios.Config.URI_APP_VISUALIZAR_USUARIO;

/**
 * Created by David on 04/06/2016.
 */

public class UsuarioVisualizar extends DatabaseTest {

    @Test
    public void visualizarUsuario_noIdentificado_response401(){
        //PRE
        String token = mTestUtils.logInUser1();
        mTestUtils.logInUser2();
        Response listResponse = WebClient.create(URI_APP_LISTAR_USUARIOS).header("Authentication", token).get();
        DataListUser[] miLista = listResponse.readEntity(DataListUser[].class);
        int idusuario = miLista[0].getId();


        //WHEN
        Response response = WebClient.create(URI_APP_VISUALIZAR_USUARIO + idusuario).get();


        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void visualizarUsuario_success_response200(){
        //PRE
        String token = mTestUtils.logInUser1();
        mTestUtils.logInUser2();
        Response listResponse = WebClient.create(URI_APP_LISTAR_USUARIOS).header("Authentication", token).get();
        DataListUser[] miLista = listResponse.readEntity(DataListUser[].class);
        int idusuario = miLista[0].getId();


        //WHEN
        Response response = WebClient.create(URI_APP_VISUALIZAR_USUARIO + idusuario).header("Authentication", token).get();


        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void visualizarMiUsuario_success_response200(){
        //PRE
        String token = mTestUtils.logInUser1();


        //WHEN
        Response response = WebClient.create(URI_APP_VISUALIZAR_USUARIO).header("Authentication", token).get();


        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

}
