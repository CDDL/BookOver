package servicios;

import modelo.datos.entidades.Intercambio;
import modelo.datos.entidades.Libro;
import modelo.datos.transferencia.DataIntercambio;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jettison.json.JSONArray;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.URI_APP_PETICION_COMPRA;
import static servicios.Config.URI_APP_PETICION_INTERCAMBIO;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionIntercambioNuevo extends DatabaseTest{

    @Test
    public void solicitarIntercambio_transaccionCorrecta_respuesta200() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookIntercambiable(token2);
        int idLibro2 = mTestUtils.registerBookIntercambiable(token1);

        //DADO
        DataIntercambio dataIntercambio = new DataIntercambio();
        dataIntercambio.setLibroSolicitante(idLibro2);
        dataIntercambio.setLibroSolicitado(idLibro1);

        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO).header("Authentication", token1).post(dataIntercambio);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }


    @Test
    public void solicitarIntercambio_usuarioNoIdentificado_respuesta401() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookIntercambiable(token1);
        int idLibro2 = mTestUtils.registerBookIntercambiable(token2);

        //DADO
        DataIntercambio dataIntercambio = new DataIntercambio();
        dataIntercambio.setLibroSolicitante(idLibro2);
        dataIntercambio.setLibroSolicitado(idLibro1);
        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO).post(dataIntercambio);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }


    @Test
    public void solicitarIntercambio_libroInexistente_respuesta404() {
        //PRE
        String token2 = mTestUtils.logInUser2();
        int idLibro2 = mTestUtils.registerBookIntercambiable(token2);

        //DADO
        DataIntercambio dataIntercambio = new DataIntercambio();
        dataIntercambio.setLibroSolicitante(idLibro2);
        dataIntercambio.setLibroSolicitado(-2);
        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO).header("Authentication", token2).post(dataIntercambio);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }


    @Test
    public void solicitarIntercambio_libroNoIntercambiable_respuesta409() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookNoPrestableIntercambiableVendible(token1);
        int idLibro2 = mTestUtils.registerBookNoPrestableIntercambiableVendible(token2);

        //DADO
        DataIntercambio dataIntercambio = new DataIntercambio();
        dataIntercambio.setLibroSolicitante(idLibro2);
        dataIntercambio.setLibroSolicitado(idLibro1);
        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO).header("Authentication", token2).post(dataIntercambio);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(409));
    }
}
