package controlador;

import controlador.security.IdentificationRequiered;
import modelo.datos.LoginData;

import modelo.datos.entidades.Usuario;
import modelo.servicios.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by David on 24/04/2016.
 */

@Path("login")
public class LoginController {
    @Inject
    UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginData login) {
        Usuario usuario = userService.getByUsername(login.getUsername());

        if (usuario != null && usuario.getPassword().equals(login.getPassword()))
            return Response.status(Response.Status.UNAUTHORIZED).build();


        return Response.status(Response.Status.ACCEPTED).entity(generateToken(login)).build();
    }

    private String generateToken(LoginData login) {

        return null;
    }


    @IdentificationRequiered
    @GET
    @Path("prueba")
    public Response prueba() {

        return Response.status(Response.Status.ACCEPTED).build();
    }
}
