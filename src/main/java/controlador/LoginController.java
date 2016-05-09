package controlador;

import modelo.datos.transfer.LoginData;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginData loginData) {
        Usuario usuario = userService.getByUsername(loginData.getUsername());
        if (usuario == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        if (!usuario.getPassword().equals(loginData.getPassword()))
            Response.status(Response.Status.CONFLICT).build();

        return Response.status(Response.Status.ACCEPTED).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("prueba")
    public Response prueba(LoginData loginData, Usuario usuario){
        System.out.println(loginData.getUsername() + " " + loginData.getPassword());
        System.out.println(usuario.getUsername()+ " " + usuario.getEmail());

        return Response.status(Response.Status.ACCEPTED).build();
    }
}
