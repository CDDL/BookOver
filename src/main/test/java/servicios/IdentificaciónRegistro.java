package servicios;
import org.apache.cxf.jaxrs.client.WebClient;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataRegister;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by Demils on 23/05/2016.
 */
public class IdentificaciónRegistro extends DatabaseTest {

    @Test
    public void registro_usuarioNuevo_respuesta200(){
        //PRE
        DataRegister usuarioNuevo = new Usuario();
        usuarioNuevo.setUsername("TestUsername");
        usuarioNuevo.setPassword("123456");
        usuarioNuevo.setEmail("email@tst.test");
        usuarioNuevo.setUbicacion("casa");


        //WHEN
        Response response = WebClient.create(URI_APP_BASE+"register").post(usuarioNuevo);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void registro_usuarioYaExistente_respuesta409(){
        //PRE
        DataRegister usuarioNuevo = new Usuario();
        usuarioNuevo.setUsername("TestUsername");
        usuarioNuevo.setPassword("123456");
        usuarioNuevo.setEmail("email@tst.test");
        usuarioNuevo.setUbicacion("casa");
        WebClient.create(URI_APP_BASE+"/register").post(usuarioNuevo);

        //WHEN
        Response response = WebClient.create(URI_APP_BASE+"register").post(usuarioNuevo);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(409));
    }
}
