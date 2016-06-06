package servicios;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListUser;
import modelo.datos.transferencia.DataLogin;
import modelo.datos.transferencia.DataProfileUser;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

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

    @DELETE
    @Path("login")
    public Response logout(@HeaderParam("Authentication") String token) {
        mTokenController.deleteToken(token);

        return status(OK)
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
        return status(OK).entity(personas).build();
    }

    @GET
    @Path("profile/{idusuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showUserProfile(@HeaderParam("Authentication") String token, @PathParam("idusuario") String idusuario) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario;
        if (idusuario == null) {
            usuario = mTokenController.getUserFromToken(token);
        } else {
            usuario = mUserController.getUserById(Integer.parseInt(idusuario));
        }
        DataProfileUser data = mUserController.visualizaUser(usuario);
        return status(OK).entity(data).build();
    }

    @GET
    @Path("profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showUserProfile(@HeaderParam("Authentication") String token) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario;
        usuario = mTokenController.getUserFromToken(token);

        DataProfileUser data = mUserController.visualizaUser(usuario);
        return status(OK).entity(data).build();
    }

    @GET
    @Path("valoraciones/{idusuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaValoraciones(@HeaderParam("Authentication") String token, @PathParam("idusuario") int idusuario) {
        Usuario usuarioIdentificado = mTokenController.getUserFromToken(token);
        Usuario usuarioTarget = mUserController.getUserById(idusuario);


        if (usuarioIdentificado == null) return status(UNAUTHORIZED).build();
        if (usuarioTarget == null) status(NOT_FOUND).build();

        List listaValoraciones = usuarioIdentificado.getListaValoraciones();

        return status(OK)
                .entity(listaValoraciones)
                .build();
    }
}
