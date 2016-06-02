package servicios;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;

/**
 * Created by David on 24/04/2016.
 */

@Path("usuarios")
@Stateless
public class ServicioUsuario {

    @Inject
    IControllerToken mTokenController;

    @Inject
    IControllerUsuario mUserController;


    @POST
    @Path("{idUsuario}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editarPerfil(@HeaderParam("Authentication") String token,@PathParam("idUsuario") int idUsuario, Usuario usuario) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        if (mTokenController.getUserFromToken(token).getId() != idUsuario) return status(FORBIDDEN).build();
        mUserController.editarDatosUsuario(idUsuario, usuario);

        return status(OK)
                .build();
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response login(DataLogin usuario) {
        if (!mUserController.existeUsuario(usuario.getUsername())) return status(UNAUTHORIZED).build();
        if (!mUserController.isLoginCorrecto(usuario)) return status(UNAUTHORIZED).build();

        return status(OK)
                .entity(mTokenController.generarToken(usuario))
                .build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response register(Usuario usuario) {
        if (mUserController.existeUsuario(usuario.getUsername())) return status(CONFLICT).build();
        mUserController.registrarUsuario(usuario);

        return status(OK)
                .entity(1)
                .build();
    }
}
