package controlador;

import modelo.datos.LoginData;
import modelo.datos.Usuario;
import modelo.servicios.UserService;
import org.apache.commons.lang.WordUtils;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginData loginData) {
        Usuario usuario = userService.getByUsername(loginData.getUsername());
        if (usuario == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        if (!usuario.getPassword().equals(loginData.getPassword()))
            Response.status(Response.Status.CONFLICT).build();

        return Response.status(Response.Status.ACCEPTED).build();
    }
}
