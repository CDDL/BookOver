package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import modelo.datos.transferencia.DataRegister;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by Demils on 31/05/2016.
 */
public class LibroNuevo extends DatabaseTest {

    @Test
    public void añadirLibro_libroCorrecto_respueta200() {
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_BASE + "usuarios").post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_BASE + "usuarios/login").post(loginData);
        Token token = loginResponse.readEntity(Token.class);

        Libro libroNuevo = new Libro();
        libroNuevo.setTitulo("Libro custom");
        libroNuevo.setAutor("yoMismo");
        libroNuevo.setEditorial("Editorial rara");
        libroNuevo.setIsbn("ISBNRANDOM");
        libroNuevo.setEstado("Roto");
        libroNuevo.setInfoAdicional("Lo encontre en la calle");
        libroNuevo.setEsPrestable(false);
        libroNuevo.setEsIntercambiable(false);
        libroNuevo.setEsVendible(false);

        //WHEN
        Response response = WebClient.create(URI_APP_BASE + "/libros").header("Authentication", token.getToken()).post(libroNuevo);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void añadirLibro_usuarioNoIdentificado_respuesta401() {
        //PRE
        Libro libroNuevo = new Libro();
        libroNuevo.setTitulo("Libro custom");
        libroNuevo.setAutor("yoMismo");
        libroNuevo.setEditorial("Editorial rara");
        libroNuevo.setIsbn("ISBNRANDOM");
        libroNuevo.setEstado("Roto");
        libroNuevo.setInfoAdicional("Lo encontre en la calle");
        libroNuevo.setEsPrestable(false);
        libroNuevo.setEsIntercambiable(false);
        libroNuevo.setEsVendible(false);

        //WHEN
        Response response = WebClient.create(URI_APP_BASE + "libros").post(libroNuevo);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }
}
