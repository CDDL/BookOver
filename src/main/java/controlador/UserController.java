package controlador;

import modelo.datos.entidades.Usuario;
import modelo.datos.transfer.LoginData;
import modelo.servicios.UserService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by David on 07/05/2016.
 */

@Path("user")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Path("{id}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserFull(@PathParam("id") int id, LoginData loginData){
        System.out.println("olae");
        String username = loginData.getUsername();
        String password = loginData.getPassword();
        if(!userService.isUserValid(username,password)){
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Usuario usuario = userService.getByUsername(username);
        if(id!= usuario.getId()){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.ok(usuario).build();
    }

}
