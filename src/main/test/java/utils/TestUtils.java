package utils;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;

import static servicios.Config.*;

/**
 * Created by Demils on 23/05/2016.
 */
public class TestUtils {
    private static Token mToken;

    void vaciarDB() {
        WebClient.create(URI_APP_BASE + "test/vaciardb").get();
    }

    public String logInUser1() {
        Usuario usuario = new Usuario();
        usuario.setUsername("test");
        usuario.setEmail("test@test.com");
        usuario.setUbicacion("madrid");
        usuario.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuario);

        DataLogin login = new Usuario();
        login.setUsername("test");
        login.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(login);
        return loginResponse.readEntity(Token.class).getToken();
    }

    public String logInUser2() {
        Usuario usuario = new Usuario();
        usuario.setUsername("test2");
        usuario.setEmail("test@test.com");
        usuario.setUbicacion("madrid");
        usuario.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuario);

        DataLogin login = new Usuario();
        login.setUsername("test2");
        login.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(login);
        return loginResponse.readEntity(Token.class).getToken();
    }

    public String logInUser3() {
        Usuario usuario = new Usuario();
        usuario.setUsername("test3");
        usuario.setEmail("test@test.com");
        usuario.setUbicacion("madrid");
        usuario.setPassword("139123");
        WebClient.create(URI_APP_REGISTER).post(usuario);

        DataLogin login = new Usuario();
        login.setUsername("test3");
        login.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_LOGIN).post(login);
        return loginResponse.readEntity(Token.class).getToken();
    }

    public int registerBookPrestable(String token) {
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
        return WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token).post(libroPrestable).readEntity(Integer.class);
    }

    public int solicitarPrestamos(String token, int idLibro) {
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro);
        return WebClient.create(URI_APP_PETICION_PRESTAMO).header("Authentication", token).post(libroASolicitar).readEntity(Integer.class);
    }

    public int registerBookNoPrestableIntercambiableVendible(String token) {
        Libro libroPrestable = new Libro();
        libroPrestable.setTitulo("Libro custom");
        libroPrestable.setAutor("yoMismo");
        libroPrestable.setEditorial("Editorial rara");
        libroPrestable.setIsbn("ISBNRANDOM");
        libroPrestable.setEstado("Roto");
        libroPrestable.setInfoAdicional("Lo encontre en la calle");
        libroPrestable.setEsPrestable(false);
        libroPrestable.setEsIntercambiable(false);
        libroPrestable.setEsVendible(false);
        return WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token).post(libroPrestable).readEntity(Integer.class);
    }

    public void aceptarPrestamo(String token1, int idTransaccion) {
        WebClient.create(URI_APP_PETICION_PRESTAMO_RECIBIDO + idTransaccion).header("Authentication", token1).put(null);
    }
}
