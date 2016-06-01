package servicios;

import modelo.datos.entidades.Usuario;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.status;

/**
 * Created by Demils on 31/05/2016.
 */
@Path("perfil")
@Stateless
public class ServicioPerfil {

    @Inject
    private IControllerToken mTokenController;

    @Inject
    private IControllerUsuario mUserController;

    @POST
    @Path("editar")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editarPerfil(@HeaderParam("Authentication") String token, Usuario usuario) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        mUserController.editarDatosUsuario(mTokenController.getUserFromToken(token).getId(), usuario);

        return status(OK)
                .build();
    }
}
