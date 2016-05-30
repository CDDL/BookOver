package servicios;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import modelo.datos.transferencia.DataRegister;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.Web;
import org.apache.openejb.jee.WebApp;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DatabaseUtils;

import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by Demils on 23/05/2016.
 */
public class IdentificaciónLogin {

    private static DatabaseUtils mDatabaseUtils;

    @BeforeClass
    public static void setUpTests(){
        mDatabaseUtils = new DatabaseUtils();
    }

    @Before
    public void vaciarDatabase(){
        mDatabaseUtils.vaciarDB();
    }

    @Test
    public void login_datosIncorrectos_respusta401(){
        //PRE
        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("o2480");

        //WHEN
        Response response = WebClient.create(URI_APP_BASE + "login").post(loginData);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(401));
    }

    @Test
    public void login_datosCorrctos_respuesta200(){
        //PRE
        DataRegister registerData = new Usuario();
        registerData.setUsername("test");
        registerData.setEmail("test@test.com");
        registerData.setUbicacion("madrid");
        registerData.setPassword("139123");
        Response res = WebClient.create(URI_APP_BASE + "register").post(registerData);

        DataLogin loginData = new Usuario();
        loginData.setUsername("test");
        loginData.setPassword("139123");

        //WHEN
        Response response = WebClient.create(URI_APP_BASE + "login").post(loginData);

        //ESPERADO
        assertThat(response.getStatusInfo().getStatusCode(), is(200));
    }
}
