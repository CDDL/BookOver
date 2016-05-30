package servicios;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by oscar on 31/01/15.
 */
public class TestServicioLibros {

    @Test
    public void register_test() {
        Response response = WebClient.create(URI_APP_BASE+"/prueba")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_XML)
                .get();

        assertThat(response.getStatusInfo().getStatusCode(), is(Response.Status.OK.getStatusCode()));
    }
}
