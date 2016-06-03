package utils;

import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;

import static servicios.Config.URI_APP_BASE;

/**
 * Created by Demils on 23/05/2016.
 */
public class DatabaseUtils {
    public void vaciarDB() {
        WebClient.create(URI_APP_BASE + "test/vaciardb").get();
    }
}
