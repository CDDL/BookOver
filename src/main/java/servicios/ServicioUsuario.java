package servicios;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListUser;
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
    @Path("editar")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editarPerfil(@HeaderParam("Authentication") String token, Usuario usuario) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        mUserController.editarDatosUsuario(mTokenController.getUserFromToken(token).getId(), usuario);

        return status(OK)
                .build();
    }

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
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response register(Usuario usuario) {
        if (mUserController.existeUsuario(usuario.getUsername())) return status(CONFLICT).build();
        mUserController.registrarUsuario(usuario);

        return status(OK)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@HeaderParam("Authentication") String token) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario = mTokenController.getUserFromToken(token);
        DataListUser[] personas = mUserController.listaOtrasPersonas(usuario);
        return Response.ok(personas).build();
    }
}
