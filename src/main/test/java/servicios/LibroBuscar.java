package servicios;

import modelo.datos.entidades.Libro;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import utils.DatabaseTest;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static servicios.Config.URI_APP_BASE;
import static servicios.Config.URI_APP_BUSCAR_LIBRO;

/**
 * Created by David on 05/06/2016.
 */
public class LibroBuscar extends DatabaseTest {

    @Test
    public void buscarLibro_noIdentificado_response401(){
        String token = mTestUtils.logInUser1();

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
        WebClient.create(URI_APP_BASE + "/libros").header("Authentication", token).post(libroNuevo);

        //WHEN
        Response response = WebClient.create(URI_APP_BUSCAR_LIBRO + "libro").get();

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));


    }

    @Test
    public void buscarLibro_success_response200(){
        String token = mTestUtils.logInUser1();

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
        WebClient.create(URI_APP_BASE + "/libros").header("Authentication", token).post(libroNuevo);
        /*libroNuevo.setTitulo("ahlele");
        libroNuevo.setAutor("libro");
        libroNuevo.setEditorial("Editorial rara");
        libroNuevo.setIsbn("ISBNRANDOM");
        libroNuevo.setEstado("Roto");
        libroNuevo.setInfoAdicional("Lo encontre en la calle");
        libroNuevo.setEsPrestable(false);
        libroNuevo.setEsIntercambiable(false);
        libroNuevo.setEsVendible(false);
        WebClient.create(URI_APP_BASE + "/libros").header("Authentication", token).post(libroNuevo);
        libroNuevo.setTitulo("ahelele");
        libroNuevo.setAutor("yoMismo");
        libroNuevo.setEditorial("libro");
        libroNuevo.setIsbn("ISBNRANDOM");
        libroNuevo.setEstado("Roto");
        libroNuevo.setInfoAdicional("Lo encontre en la calle");
        libroNuevo.setEsPrestable(false);
        libroNuevo.setEsIntercambiable(false);
        libroNuevo.setEsVendible(false);
        WebClient.create(URI_APP_BASE + "/libros").header("Authentication", token).post(libroNuevo);*/

        //WHEN
        Response response = WebClient.create(URI_APP_BUSCAR_LIBRO + "libro").header("Authentication", token).get();
        Libro[] miLista = response.readEntity(Libro[].class);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
        assertThat(miLista.length,is(1));



    }


}
