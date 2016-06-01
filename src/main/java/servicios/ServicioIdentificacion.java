package servicios;

import modelo.datos.entidades.Usuario;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;

/**
 * Created by David on 24/04/2016.
 */

@Path("")
@Stateless
public class ServicioIdentificacion {

    @Inject
    IControllerToken mTokenController;

    @Inject
    IControllerUsuario mUserController;

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response login(Usuario usuario) {
        if (!mUserController.existeUsuario(usuario.getUsername())) return status(UNAUTHORIZED).build();
        if (!mUserController.isLoginCorrecto(usuario)) return status(UNAUTHORIZED).build();

        return status(OK)
                .entity(mTokenController.generarToken(usuario))
                .build();
    }

    @POST
    @Path("register")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response register(Usuario usuario) {
        if (mUserController.existeUsuario(usuario.getUsername())) return status(CONFLICT).build();
        mUserController.registrarUsuario(usuario);

        return status(OK)
                .build();

    }
}
