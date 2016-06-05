package servicios;

import modelo.datos.entidades.Valoracion;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.URI_APP_PETICION_PRESTAMO_ACEPTAR;
import static servicios.Config.URI_APP_TRANSACCION_VALORAR;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionValoracionNueva extends DatabaseTest{

    @Test
    public void valoracionNueva_valoracionCorrecta_respuesta200() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);
        mTestUtils.aceptarTransaccion(token1, idTransaccion);
        mTestUtils.confirmarPrestamoRecibido(token2, idTransaccion);
        mTestUtils.confirmarPrestamoDevuelto(token1, idTransaccion);

        //DADO
        Valoracion valoracion = new Valoracion();
        valoracion.setDescripcion("Muy guay");
        valoracion.setPuntuacion(5);

        Response response = WebClient.create(URI_APP_TRANSACCION_VALORAR + idTransaccion).header("Authentication", token1).put(valoracion);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void valoracionNueva_noIdentificado_respuesta401() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);
        mTestUtils.aceptarTransaccion(token1, idTransaccion);
        mTestUtils.confirmarPrestamoRecibido(token2, idTransaccion);
        mTestUtils.confirmarPrestamoDevuelto(token1, idTransaccion);

        //DADO
        Valoracion valoracion = new Valoracion();
        valoracion.setDescripcion("Muy guay");
        valoracion.setPuntuacion(5);

        Response response = WebClient.create(URI_APP_TRANSACCION_VALORAR + idTransaccion).put(valoracion);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void valoracionNueva_transaccionAjena_respuesta403() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        String token3 = mTestUtils.logInUser3();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);
        mTestUtils.aceptarTransaccion(token1, idTransaccion);
        mTestUtils.confirmarPrestamoRecibido(token2, idTransaccion);
        mTestUtils.confirmarPrestamoDevuelto(token1, idTransaccion);

        //DADO
        Valoracion valoracion = new Valoracion();
        valoracion.setDescripcion("Muy guay");
        valoracion.setPuntuacion(5);

        Response response = WebClient.create(URI_APP_TRANSACCION_VALORAR + idTransaccion).header("Authentication", token3).put(valoracion);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(403));
    }

    @Test
    public void valoracionNueva_transaccionInexistente_respuesta404() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);
        mTestUtils.aceptarTransaccion(token1, idTransaccion);
        mTestUtils.confirmarPrestamoRecibido(token2, idTransaccion);
        mTestUtils.confirmarPrestamoDevuelto(token1, idTransaccion);

        //DADO
        Valoracion valoracion = new Valoracion();
        valoracion.setDescripcion("Muy guay");
        valoracion.setPuntuacion(5);

        Response response = WebClient.create(URI_APP_TRANSACCION_VALORAR + -4).header("Authentication", token1).put(valoracion);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }

    @Test
    public void valoracionNueva_transaccionNoAceptada_respuesta409() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);

        //DADO
        Valoracion valoracion = new Valoracion();
        valoracion.setDescripcion("Muy guay");
        valoracion.setPuntuacion(5);

        Response response = WebClient.create(URI_APP_TRANSACCION_VALORAR + idTransaccion).header("Authentication", token1).put(valoracion);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(409));
    }
}
