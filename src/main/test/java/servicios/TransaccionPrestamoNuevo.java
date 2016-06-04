package servicios;

import modelo.datos.entidades.Libro;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.*;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionPrestamoNuevo extends DatabaseTest{

    @Test
    public void nuevaTransaccion_transaccionCorrecta_respuesta200() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookPrestable(token1);

        //DADO
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro1);
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO).header("Authentication", token2).post(libroASolicitar);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }


    @Test
    public void nuevaTransaccion_usuarioNoIdentificado_respuesta401() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        int idLibro1 = mTestUtils.registerBookPrestable(token1);

        //DADO
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro1);
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO).post(libroASolicitar);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }


    @Test
    public void nuevaTransaccion_libroInexistente_respuesta404() {
        //PRE
        String token1 = mTestUtils.logInUser1();

        //DADO
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(-1);
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO).header("Authentication", token1).post(libroASolicitar);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }


    @Test
    public void nuevaTransaccion_libroNoPrestable_respuesta409() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookNoPrestableIntercambiableVendible(token1);

        //DADO
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro1);
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO).header("Authentication", token2).post(libroASolicitar);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(409));
    }
}
