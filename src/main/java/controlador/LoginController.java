package controlador;

import controlador.filterSecurity.IdentificationRequiered;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transfer.LoginData;
import modelo.servicios.TokenService;
import modelo.servicios.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * Created by David on 24/04/2016.
 */

@Path("login")
public class LoginController {
    @Inject
    TokenService tokenService;

    @Inject
    UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginData login) {
        String mUsername = login.getUsername();
        String mPassword = login.getPassword();
        Usuario usuario = userService.getByUsername(mUsername);

        if(usuario == null) return Response.status(UNAUTHORIZED).build();
        if(!Objects.equals(usuario.getPassword(), mPassword)) return Response.status(UNAUTHORIZED).build();

        Token token = generarToken(usuario);
        tokenService.add(token);

        return Response
                .status(ACCEPTED)
                .entity(token.getToken())
                .build();
    }

    private Token generarToken(Usuario usuario) {
        Token token = new Token();
        token.setUsuario(usuario);
        token.setToken(generarStringRandom());
        return token;
    }

    private String generarStringRandom() {
        return UUID.randomUUID().toString();
    }


    @IdentificationRequiered
    @GET
    @Path("prueba")
    public Response prueba() {
        return Response.status(ACCEPTED).build();
    }

    @GET
    @Path("prueba2")
    public Response prueba2() {
        return Response.status(ACCEPTED).build();
    }
}
