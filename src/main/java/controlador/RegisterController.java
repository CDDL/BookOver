package controlador;

import modelo.datos.entidades.Usuario;
import modelo.servicios.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.*;

/**
 * Created by David on 24/04/2016.
 */
@Path("register")
public class RegisterController {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Usuario usuario) {
        Usuario usuarioSistema = userService.getByUsername(usuario.getUsername());
        if (usuarioSistema != null)
            return Response.status(Response.Status.CONFLICT).build();

        Usuario myUsuario = userService.add(usuario);
        return Response.ok(myUsuario).build();
    }

    /*@GET
    @Path("{multiplicando}")
    public int duplica(@PathParam("multiplicando") int multiplicando) {
        return 2 * multiplicando;
    }

    @GET
    @Path("new")
    @Produces(MediaType.TEXT_PLAIN)
    public String newMethod(@QueryParam("name") String name, @QueryParam("surname") String surname) {
        return name.toUpperCase() + "_" + surname.toUpperCase();
    }*/
}
