package servicios;

import modelo.datos.transferencia.DataIntercambio;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.URI_APP_PETICION_INTERCAMBIO;
import static servicios.Config.URI_APP_PETICION_INTERCAMBIO_REALIZADO;
import static servicios.Config.URI_APP_PETICION_VENTA_REALIZADA;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionIntercambioRealizado extends DatabaseTest {

    @Test
    public void intercambioRealizado_confirmacionCorrecta_respuesta200() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookIntercambiable(token1);
        int idLibro2 = mTestUtils.registerBookIntercambiable(token2);
        int idTransaccion = mTestUtils.solicitarIntercambio(token1, idLibro1, idLibro2);
        mTestUtils.aceptarTransaccion(token2, idTransaccion);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO_REALIZADO + idTransaccion).header("Authentication", token2).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }


    @Test
    public void intercambioRealizado_usuarioNoIdentificado_respuesta401() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookIntercambiable(token1);
        int idLibro2 = mTestUtils.registerBookIntercambiable(token2);
        int idTransaccion = mTestUtils.solicitarIntercambio(token1, idLibro1, idLibro2);
        mTestUtils.aceptarTransaccion(token2, idTransaccion);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO_REALIZADO + idTransaccion).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void intercambioRealizado_transaccionNoPerteneceAlUsuario_respuesta403() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        String token3 = mTestUtils.logInUser3();
        int idLibro1 = mTestUtils.registerBookIntercambiable(token1);
        int idLibro2 = mTestUtils.registerBookIntercambiable(token2);
        int idTransaccion = mTestUtils.solicitarIntercambio(token1, idLibro1, idLibro2);
        mTestUtils.aceptarTransaccion(token2, idTransaccion);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO_REALIZADO + idTransaccion).header("Authentication", token3).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(403));
    }

    @Test
    public void intercambioRealizado_transaccionInexistente_respuesta404() {
        //PRE
        String token1 = mTestUtils.logInUser1();

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO_REALIZADO + -1).header("Authentication", token1).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }


    @Test
    public void intercambioRealizado_libroYaIntercambiado_respuesta409() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookIntercambiable(token1);
        int idLibro2 = mTestUtils.registerBookIntercambiable(token2);
        int idTransaccion = mTestUtils.solicitarIntercambio(token1, idLibro1, idLibro2);
        mTestUtils.aceptarTransaccion(token2, idTransaccion);
        mTestUtils.confirmarIntercambioRealizado(token2, idTransaccion);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO_REALIZADO + idTransaccion).header("Authentication", token2).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(409));
    }
}
