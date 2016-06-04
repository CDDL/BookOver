package servicios;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.URI_APP_PETICION_PRESTAMO_RECIBIDO;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionPrestamoRecibido extends DatabaseTest {

    @Test
    public void prestamoRecibido_confirmacionCorrecta_respuesta200() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro1);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_RECIBIDO + idTransaccion).header("Authentication", token1).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }


    @Test
    public void prestamoRecibido_usuarioNoIdentificado_respuesta401() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro1);


        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_RECIBIDO + idTransaccion).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void prestamoRecibido_transaccionNoPerteneceAlUsuario_respuesta403() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        String token3 = mTestUtils.logInUser3();
        int idLibro1 = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro1);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_RECIBIDO + idTransaccion).header("Authentication", token3).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void prestamoRecibido_transaccionInexistente_respuesta404() {
        //PRE
        String token1 = mTestUtils.logInUser1();

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_RECIBIDO + -1).header("Authentication", token1).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }


    @Test
    public void prestamoRecibido_libroYaRecibido_respuesta409() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro1);
        mTestUtils.aceptarPrestamo(token1, idTransaccion);
        mTestUtils.confirmarPrestamoRecibido(token2, idTransaccion);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_RECIBIDO + idTransaccion).header("Authentication", token1).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(409));
    }
}
