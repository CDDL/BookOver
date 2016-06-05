package utils;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataIntercambio;
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
        if (loginResponse.getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Login de user 1 ha fallado");
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
        if (loginResponse.getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Login de user 2 ha fallado");
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
        if (loginResponse.getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Login de user 3 ha fallado");
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
        Response response = WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token).post(libroPrestable);
        if (response.getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Registrar libro prestable fallido.");
        return response.readEntity(Integer.class);
    }

    public int solicitarPrestamos(String token, int idLibro) {
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro);
        Response response = WebClient.create(URI_APP_PETICION_PRESTAMO).header("Authentication", token).post(libroASolicitar);
        if (response.getStatusInfo().getStatusCode() != 200) throw new RuntimeException("Solicitar prestamo fallido.");
        return response.readEntity(Integer.class);
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
        Response response = WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token).post(libroPrestable);
        if (response.getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Registrar libro no prestable/intercambiable/vendible fallido.");
        return response.readEntity(Integer.class);
    }

    public void aceptarTransaccion(String token, int idTransaccion) {
        if (WebClient.create(URI_APP_PETICION_PRESTAMO_ACEPTAR + idTransaccion).header("Authentication", token).put(null).getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Aceptar prestamo fallido.");
    }

    public void confirmarPrestamoRecibido(String token, int idTransaccion) {
        if (WebClient.create(URI_APP_PETICION_PRESTAMO_RECIBIDO + idTransaccion).header("Authentication", token).put(null).getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Confirmar prestamo fallido.");
    }

    public void confirmarPrestamoDevuelto(String token, int idTransaccion) {
        if (WebClient.create(URI_APP_PETICION_PRESTAMO_DEVUELTO + idTransaccion).header("Authentication", token).put(null).getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Confirmar devolucion fallido.");
    }

    public int registerBookVendible(String token) {
        Libro libroPrestable = new Libro();
        libroPrestable.setTitulo("Libro custom");
        libroPrestable.setAutor("yoMismo");
        libroPrestable.setEditorial("Editorial rara");
        libroPrestable.setIsbn("ISBNRANDOM");
        libroPrestable.setEstado("Roto");
        libroPrestable.setInfoAdicional("Lo encontre en la calle");
        libroPrestable.setEsPrestable(false);
        libroPrestable.setEsIntercambiable(false);
        libroPrestable.setEsVendible(true);
        Response response = WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token).post(libroPrestable);
        if (response.getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Registrar libro vendible fallido.");
        return response.readEntity(Integer.class);
    }

    public int solicitarCompra(String token, int idLibro) {
        Libro libroASolicitar = new Libro();
        libroASolicitar.setId(idLibro);
        Response response = WebClient.create(URI_APP_PETICION_COMPRA).header("Authentication", token).post(libroASolicitar);
        if (response.getStatusInfo().getStatusCode() != 200) throw new RuntimeException("Solicitar compra fallido.");
        return response.readEntity(Integer.class);
    }

    public void confirmarVentaRealizada(String token, int idTransaccion) {
        if (WebClient.create(URI_APP_PETICION_VENTA_REALIZADA + idTransaccion).header("Authentication", token).put(null).getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Confirmar venta realizada fallido");

    }

    public int registerBookIntercambiable(String token) {
        Libro libroPrestable = new Libro();
        libroPrestable.setTitulo("Libro custom");
        libroPrestable.setAutor("yoMismo");
        libroPrestable.setEditorial("Editorial rara");
        libroPrestable.setIsbn("ISBNRANDOM");
        libroPrestable.setEstado("Roto");
        libroPrestable.setInfoAdicional("Lo encontre en la calle");
        libroPrestable.setEsPrestable(false);
        libroPrestable.setEsIntercambiable(true);
        libroPrestable.setEsVendible(false);
        Response response = WebClient.create(URI_APP_NUEVO_LIBRO).header("Authentication", token).post(libroPrestable);
        if (response.getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Registrar libro intercambiable fallido.");
        return response.readEntity(Integer.class);
    }

    public int solicitarIntercambio(String token, int idLibro1, int idLibro2) {
        DataIntercambio dataIntercambio = new DataIntercambio();
        dataIntercambio.setLibroSolicitante(idLibro1);
        dataIntercambio.setLibroSolicitado(idLibro2);

        Response response = WebClient.create(URI_APP_PETICION_INTERCAMBIO).header("Authentication", token).post(dataIntercambio);
        if (response.getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Solicitar intercambio fallido.");
        return response.readEntity(Integer.class);
    }

    public void confirmarIntercambioRealizado(String token, int idTransaccion) {
        if (WebClient.create(URI_APP_PETICION_INTERCAMBIO_REALIZADO + idTransaccion).header("Authentication", token).put(null).getStatusInfo().getStatusCode() != 200)
            throw new RuntimeException("Confirmar intercambio fallido");
    }
}
