package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static servicios.Config.*;

/**
 * Created by Demils on 02/06/2016.
 */
public class TransaccionAceptar {

    @Test
    public void nuevaTransaccion_transaccionCorrecta_respuesta200() {
        //PRE
        Usuario usaurio1 = new Usuario();
        usaurio1.setUsername("test");
        usaurio1.setEmail("test@test.com");
        usaurio1.setUbicacion("madrid");
        usaurio1.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usaurio1);

        DataLogin loginUsuario1 = new Usuario();
        loginUsuario1.setUsername("test");
        loginUsuario1.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(loginUsuario1);
        Token tokenUsuario1 = loginResponse.readEntity(Token.class);

        Libro libroPrestable = new Libro();
        libroPrestable.setTitulo("Libro custom");
        libroPrestable.setAutor("yoMismo");
        libroPrestable.setEditorial("Editorial rara");
        libroPrestable.setIsbn("ISBNRANDOM");
        libroPrestable.setEstado("Roto");
        libroPrestable.setInfoAdicional("Lo encontre en la calle");
        libroPrestable.setEsPrestable(true);
        libroPrestable.setEsIntercambiable(false);
        libroPrestable.setEsVendible(false);
        Response libroResponse = WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", tokenUsuario1.getToken()).post(libroPrestable);
        int idlibro = libroResponse.readEntity(Integer.class);

        Usuario usaurio2 = new Usuario();
        usaurio2.setUsername("test2");
        usaurio2.setEmail("test@test.com");
        usaurio2.setUbicacion("madrid");
        usaurio2.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usaurio2);

        DataLogin loginUsuario2 = new Usuario();
        loginUsuario2.setUsername("test2");
        loginUsuario2.setPassword("139123");
        loginResponse = WebClient.create(URI_APP_LOGIN).post(loginUsuario2);
        Token tokenUsuario2 = loginResponse.readEntity(Token.class);

        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idlibro);
        Response resposneTransaccion = WebClient.create(URI_APP_PETICION_PRESTAMO).header("Authentication", tokenUsuario2.getToken()).post(libroASolicitar);
        int idTransaccion = resposneTransaccion.readEntity(Integer.class);

        //DADO
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO_ACEPTAR + idTransaccion).header("Authentication", tokenUsuario1.getToken()).put(null);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }
}
