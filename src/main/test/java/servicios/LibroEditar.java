package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.swing.border.LineBorder;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by David on 01/06/2016.
 */
public class LibroEditar extends DatabaseTest{

    @Test
    public void editarLibro_edicionSuccess_respuesta200(){
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_BASE + "register").post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_BASE + "login").post(loginData);
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
        Response libroResponse = WebClient.create(URI_APP_BASE + "/libros").header("Authentication", token.getToken()).post(libroNuevo);
        Libro libro = libroResponse.readEntity(Libro.class);


        //WHEN
        libroNuevo.setTitulo("nipaa");
        libroNuevo.setEstado("Nuevo");
        Response response = WebClient.create(URI_APP_BASE + "/libros/" + libro.getId()).header("Authentication", token.getToken()).put(libroNuevo);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }

    @Test
    public void editarLibro_usuarioNoIdentificado_respuesta401() {
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_BASE + "register").post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_BASE + "login").post(loginData);
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
        Response libroResponse = WebClient.create(URI_APP_BASE + "/libro/nuevo").header("Authentication", token.getToken()).post(libroNuevo);
        Libro libro = libroResponse.readEntity(Libro.class);


        //WHEN
        libroNuevo.setTitulo("nipaa");
        libroNuevo.setEstado("Nuevo");
        Response response = WebClient.create(URI_APP_BASE + "libro/"+ libro.getId()).put(libroNuevo);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));

    }

    @Test
    public void editarLibro_usuarioNoPropietario_respuesta403(){
        //PRE
        Usuario usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("test");
        usuarioEnDatabase.setEmail("test@test.com");
        usuarioEnDatabase.setUbicacion("madrid");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_BASE + "register").post(usuarioEnDatabase);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");
        Response loginResponse = WebClient.create(URI_APP_BASE + "login").post(loginData);
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
        WebClient.create(URI_APP_BASE + "/libro/nuevo").header("Authentication", token.getToken()).post(libroNuevo);

        //WHEN
        usuarioEnDatabase = new Usuario();
        usuarioEnDatabase.setUsername("david");
        usuarioEnDatabase.setEmail("david@test.com");
        usuarioEnDatabase.setUbicacion("cs");
        usuarioEnDatabase.setPassword("139123");
        WebClient.create(URI_APP_BASE + "register").post(usuarioEnDatabase);
        loginData = new Usuario();
        loginData.setUsername("david");
        loginData.setPassword("139123");
        loginResponse = WebClient.create(URI_APP_BASE + "login").post(loginData);
        token = loginResponse.readEntity(Token.class);

        libroNuevo.setTitulo("Te he hackeado el libro");
        Response response = WebClient.create(URI_APP_BASE + "/libro/editar").header("Authentication", token.getToken()).put(libroNuevo);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(403));
    }
}
