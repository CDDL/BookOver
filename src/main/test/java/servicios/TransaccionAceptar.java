package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import org.apache.cxf.jaxrs.client.WebClient;
import org.hsqldb.Database;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.*;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionAceptar  extends DatabaseTest{

    @Test
    public void aceptarTransaccion_transaccionCorrecta_respuesta200() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_ACEPTAR + idTransaccion).header("Authentication", token1).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void aceptarTransaccion_noIdentificado_respuesta401() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_ACEPTAR + idTransaccion).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void aceptarTransaccion_transaccionAjena_respuesta403() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        String token3 = mTestUtils.logInUser3();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_ACEPTAR + idTransaccion).header("Authentication", token3).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(403));
    }

    @Test
    public void aceptarTransaccion_transaccionInexistente_respuesta404() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_ACEPTAR + -1).header("Authentication", token1).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }

    @Test
    public void aceptarTransaccion_transaccionYaAceptada_respuesta409() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);
        mTestUtils.aceptarPrestamo(token1, idTransaccion);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_ACEPTAR + idTransaccion).header("Authentication", token1).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(409));
    }
}
