package servicios;

import modelo.persistencia.JpaTest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Demils on 23/05/2016.
 */
@Path("test")
@Stateless
public class ServicioTest {

    @Inject
    JpaTest jpaTest;

    @GET
    @Path("vaciardb")
    public Response vaciarDatabase(){
        jpaTest.vaciarDb();

        return Response.ok().build();
    }
}
