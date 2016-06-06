package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by David on 06/06/2016.
 */
public class LibroObtener extends DatabaseTest {


    @Test
    public void obtenerLibro_noIdentificado_response401(){
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
        Response libroResponse = WebClient.create(URI_APP_BASE + "libros").header("Authentication", token.getToken()).post(libroNuevo);
        int idlibro = libroResponse.readEntity(Integer.class);


        //WHEN

        Response response = WebClient.create(URI_APP_BASE + "libros/" + idlibro).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void obtenerLibro_notfound_response404(){
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

        //WHEN

        Response response = WebClient.create(URI_APP_BASE + "libros/" + 5).header("Authentication", token.getToken()).get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }

    @Test
    public void obtenerLibro_success_response200(){
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
        Response libroResponse = WebClient.create(URI_APP_BASE + "libros").header("Authentication", token.getToken()).post(libroNuevo);
        int idlibro = libroResponse.readEntity(Integer.class);


        //WHEN

        Response response = WebClient.create(URI_APP_BASE + "libros/" + idlibro).header("Authentication", token.getToken()).get();
        Libro libro = response.readEntity(Libro.class);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }
}
