package controlador;

import modelo.datos.Usuario;
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("user") String user, @FormParam("password") String password) {
        Usuario usuario = userService.getByUsername(user);
        if(usuario==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(!usuario.getPassword().equals(password)){
            Response.status(Response.Status.CONFLICT).build();
        }

        return Response.status(Response.Status.ACCEPTED).build();
    }
}
