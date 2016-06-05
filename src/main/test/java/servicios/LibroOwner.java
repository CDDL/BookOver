package servicios;

import modelo.datos.transferencia.DataProfileUser;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.URI_APP_BASE;
import static servicios.Config.URI_APP_OWNER_LIBRO;

/**
 * Created by David on 05/06/2016.
 */
public class LibroOwner extends DatabaseTest {


    @Test
    public void obtenerOwnerLibro_noIdentificado_response401(){
        //PRE
        String token = mTestUtils.logInUser1();
        int idLibro = mTestUtils.registerBookNoPrestableIntercambiableVendible(token);
        String token2 = mTestUtils.logInUser2();

        //WHEN
        Response response = WebClient.create(URI_APP_OWNER_LIBRO + idLibro).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));

    }

    @Test
    public void obtenerOwnerLibro_notFound_response404(){
        //PRE
        String token = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();

        //WHEN
        Response response = WebClient.create(URI_APP_OWNER_LIBRO + 5).header("Authentication", token2).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));

    }

    @Test
    public void obtenerOwnerLibro_success_response200(){
        //PRE
        String token = mTestUtils.logInUser1();
        int idLibro = mTestUtils.registerBookNoPrestableIntercambiableVendible(token);
        String token2 = mTestUtils.logInUser2();

        //WHEN
        Response response = WebClient.create(URI_APP_OWNER_LIBRO + idLibro).header("Authentication", token2).get();
        DataProfileUser resultado = response.readEntity(DataProfileUser.class);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));


    }
}
