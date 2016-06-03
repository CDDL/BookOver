package utils;

import org.junit.Before;
import org.junit.BeforeClass;
import utils.DatabaseUtils;

import org.apache.cxf.jaxrs.client.WebClient;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataRegister;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static servicios.Config.URI_APP_BASE;

/**
 * Created by Demils on 31/05/2016.
 */
public abstract class DatabaseTest {

    private static DatabaseUtils mDatabaseUtils;

    @BeforeClass
    public static void setUpTests(){
        mDatabaseUtils = new DatabaseUtils();
    }

    @Before
    public void vaciarDatabase(){
        mDatabaseUtils.vaciarDB();
    }
}
