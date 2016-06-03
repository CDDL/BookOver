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
import static servicios.Config.*;

/**
 * Created by David on 02/06/2016.
 */
public class LibroRetirar extends DatabaseTest {

    @Test
    public void retirarLibro_success_response200(){
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(loginData);
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
        Response libroResponse = WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token.getToken()).post(libroNuevo);
        int idlibro = libroResponse.readEntity(Integer.class);

        //WHEN
        Response response = WebClient.create(URI_APP_BORRAR_LIBRO + idlibro).header("Authentication", token.getToken()).delete();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void retirarLibro_noIdentification_response401(){
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(loginData);
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
        Response libroResponse = WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token.getToken()).post(libroNuevo);
        int idlibro = libroResponse.readEntity(Integer.class);

        //WHEN
        Response response = WebClient.create(URI_APP_BORRAR_LIBRO + idlibro).delete();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));

    }

    @Test
    public void retirarLibro_nopertenece_response403(){
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(loginData);
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
        Response libroResponse = WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token.getToken()).post(libroNuevo);
        int idlibro = libroResponse.readEntity(Integer.class);

        //WHEN
        //WHEN
        usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("david");
        usuarioEnDatabase.setEmail("david@test.com");
        usuarioEnDatabase.setUbicacion("cs");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_BASE + "usuarios").post(usuarioEnDatabase);
        loginData = new Usuario();
        loginData.setUsername("david");
        loginData.setPassword("139123");
        loginResponse = WebClient.create(URI_APP_BASE + "usuarios/login").post(loginData);
        token = loginResponse.readEntity(Token.class);

        Response response = WebClient.create(URI_APP_BORRAR_LIBRO + idlibro).header("Authentication", token.getToken()).delete();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(403));
    }

    @Test
    public void retirarLibro_noencontrado_response404() {
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(loginData);
        Token token = loginResponse.readEntity(Token.class);

        Response response = WebClient.create(URI_APP_BORRAR_LIBRO + 1).header("Authentication", token.getToken()).delete();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(404));
    }

}
