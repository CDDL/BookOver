package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.transferencia.DataListUser;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.URI_APP_BORRAR_LIBRO;
import static servicios.Config.URI_APP_LISTAR_USUARIOS;
import static servicios.Config.URI_APP_VISUALIZAR_LIBROS;

/**
 * Created by David on 05/06/2016.
 */
public class LibroVisualizar extends DatabaseTest {

    @Test
    public void visualizarLibro_noIdentificado_response401(){

        String token = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idlibro = mTestUtils.registerBookNoPrestableIntercambiableVendible(token2);
        Response responseusers = WebClient.create(URI_APP_LISTAR_USUARIOS).header("Authentication", token).get();
        DataListUser[] miLista = responseusers.readEntity(DataListUser[].class);
        int iduser = miLista[0].getId();

        //WHEN
        Response response = WebClient.create(URI_APP_VISUALIZAR_LIBROS + iduser).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));

    }

    @Test
    public void visualizarLibro_inexistente_response404(){
        String token = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
         mTestUtils.registerBookNoPrestableIntercambiableVendible(token2);

        //WHEN
        //WebClient.create(URI_APP_BORRAR_LIBRO + idlibro).header("Authentication", token).delete();
        Response response = WebClient.create(URI_APP_VISUALIZAR_LIBROS + 5).header("Authentication", token).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));


    }

    @Test
    public void visualizarLibro_success_response200(){
        String token = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idlibro = mTestUtils.registerBookNoPrestableIntercambiableVendible(token2);
        Response responseusers = WebClient.create(URI_APP_LISTAR_USUARIOS).header("Authentication", token).get();
        DataListUser[] miLista = responseusers.readEntity(DataListUser[].class);
        int iduser = miLista[0].getId();

        //WHEN
        Response response = WebClient.create(URI_APP_VISUALIZAR_LIBROS + iduser).header("Authentication", token).get();
        Libro[] miListaLibros =  response.readEntity(Libro[].class);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
        assertThat(miListaLibros.length, is(1));

    }


}
