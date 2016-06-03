package servicios;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataConversacion;
import modelo.datos.transferencia.DataListConversaciones;
import modelo.datos.transferencia.DataMensaje;
import servicios.comunicacionControlador.IControllerMensaje;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.status;

/**
 * Created by David on 02/06/2016.
 */

@Path("conversaciones")
@Stateless
public class ServicioMensaje {

        @Inject
        IControllerToken mTokenController;

        @Inject
        IControllerMensaje mMensajeController;

        @Inject
        IControllerUsuario mUsuarioController;

    @POST
    @Path("mensaje")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response enviarMensaje(@HeaderParam("Authentication") String token, DataMensaje dataMensaje) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario = mTokenController.getUserFromToken(token);
        Usuario otroUser = mUsuarioController.getUserById(dataMensaje.getPara());
        if(otroUser==null){
            return status(NOT_FOUND).build();
        }
        int idmensaje = mMensajeController.enviarMensaje(dataMensaje,usuario,otroUser);
        return status(OK).entity(idmensaje).build();

    }

    @GET
    @Path("listaConversaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showListConversaciones(@HeaderParam("Authentication") String token){
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario = mTokenController.getUserFromToken(token);
        DataListConversaciones[] resultado = mMensajeController.findListConversaciones(usuario);
        return status(OK).entity(resultado).build();
    }

    @GET
    @Path("{idConversacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showConversacion(@HeaderParam("Authentication") String token, @PathParam("idConversacion") int idConversacion){
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario = mTokenController.getUserFromToken(token);
        if(!mMensajeController.participaUser(usuario, idConversacion)) return status(UNAUTHORIZED).build();
        DataConversacion[] resultado = mMensajeController.findDataConversacion(idConversacion);
        return status(OK).entity(resultado).build();
    }
}
