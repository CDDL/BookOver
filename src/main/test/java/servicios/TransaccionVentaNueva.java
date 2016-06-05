package servicios;

import modelo.datos.entidades.Libro;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.URI_APP_PETICION_COMPRA;
import static servicios.Config.URI_APP_PETICION_PRESTAMO;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionVentaNueva extends DatabaseTest{

    @Test
    public void solicitarComopra_transaccionCorrecta_respuesta200() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        String token2 = mTestUtils.logInUser2();
        int idLibro1 = mTestUtils.registerBookVendible(token1);

        //DADO
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro1);
        Response response = WebClient.create(URI_APP_PETICION_COMPRA).header("Authentication", token2).post(libroASolicitar);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }


    @Test
    public void solicitarComopra_usuarioNoIdentificado_respuesta401() {
        //PRE
        String token1 = mTestUtils.logInUser1();
        int idLibro1 = mTestUtils.registerBookVendible(token1);

        //DADO
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro1);
        Response response = WebClient.create(URI_APP_PETICION_COMPRA).post(libroASolicitar);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }


    @Test
    public void solicitarComopra_libroInexistente_respuesta404() {
        //PRE
        String token1 = mTestUtils.logInUser1();

        //DADO
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(-2);
        Response response = WebClient.create(URI_APP_PETICION_COMPRA).header("Authentication", token1).post(libroASolicitar);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }


    @Test
    public void solicitarComopra_libroNoVendible_respuesta409() {
        //PRE
        String token2 = mTestUtils.logInUser2();
        String token1 = mTestUtils.logInUser1();
        int idLibro1 = mTestUtils.registerBookNoPrestableIntercambiableVendible(token1);

        //DADO
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro1);
        Response response = WebClient.create(URI_APP_PETICION_COMPRA).header("Authentication", token2).post(libroASolicitar);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(409));
    }
}
