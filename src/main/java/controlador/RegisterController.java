package controlador;

import modelo.datos.entidades.Usuario;
import modelo.servicios.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.*;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.OK;

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
        if(existeUsuario(usuario)) return Response.status(CONFLICT).build();
        Usuario mUsuario = userService.add(usuario);

        return Response
                .status(OK)
                .entity(mUsuario)
                .build();
    }

    private boolean existeUsuario(Usuario usuario) {
        String mUsername = usuario.getUsername();
        Usuario mUsuario = userService.getByUsername(mUsername);
        return mUsuario != null;
    }
}
