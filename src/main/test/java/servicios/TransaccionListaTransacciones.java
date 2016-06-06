package servicios;

import modelo.datos.entidades.Valoracion;
import modelo.datos.transferencia.DataIntercambio;
import modelo.datos.transferencia.DataTransacciones;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import sun.net.www.protocol.http.HttpURLConnection;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.URI_APP_PETICION_INTERCAMBIO;
import static servicios.Config.URI_APP_TRANSACCION_LISTAR;
import static servicios.Config.URI_APP_TRANSACCION_VALORAR;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionListaTransacciones extends DatabaseTest{

    @Test
    public void listaTransacciones_peticionCorrecta_respuesta200() throws IOException {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);
        mTestUtils.aceptarTransaccion(token1, idTransaccion);
        mTestUtils.confirmarPrestamoRecibido(token2, idTransaccion);
        mTestUtils.confirmarPrestamoDevuelto(token1, idTransaccion);

        //DADO
        Response response = WebClient.create(URI_APP_TRANSACCION_LISTAR).header("Authentication", token1).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }


    @Test
    public void listaTransacciones_usuarioNoIdentificado_respuesta401() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro = mTestUtils.registerBookPrestable(token1);
        int idTransaccion = mTestUtils.solicitarPrestamos(token2, idLibro);
        mTestUtils.aceptarTransaccion(token1, idTransaccion);
        mTestUtils.confirmarPrestamoRecibido(token2, idTransaccion);
        mTestUtils.confirmarPrestamoDevuelto(token1, idTransaccion);

        //DADO

        Response response = WebClient.create(URI_APP_TRANSACCION_LISTAR).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

}
