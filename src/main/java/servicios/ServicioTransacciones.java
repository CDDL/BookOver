package servicios;

import modelo.datos.entidades.Prestamo;
import modelo.datos.entidades.Transaccion;
import modelo.datos.entidades.Usuario;
import servicios.comunicacionControlador.IControllerLibro;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerTransaccion;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.status;

/**
 * Created by Demils on 03/06/2016.
 */
@Path("transacciones")
public class ServicioTransacciones {
    @Inject
    IControllerTransaccion mControllerTransaccion;

    @Inject
    IControllerLibro mControllerLibro;

    @Inject
    IControllerToken mControllerToken;

    @Path("aceptar/{idTransaccion}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response confirmarSolicitud(@HeaderParam("Authentication") String token, @PathParam("idTransaccion") int idTransaccion) {
        Usuario usuarioQuePresta = mControllerToken.getUserFromToken(token);
        Transaccion transaccion = mControllerTransaccion.getTransaccion(idTransaccion);

        if(usuarioQuePresta == null) return status(UNAUTHORIZED).build();
        if(transaccion == null) return status(NOT_FOUND).build();
        if(!transaccion.getUsuarioRecibeTransaccion().equals(usuarioQuePresta)) return status(FORBIDDEN).build();
        if(transaccion.getAceptada()) return status(CONFLICT).build();

        transaccion.setAceptada(true);
        mControllerTransaccion.actualizar(transaccion);

        return status(OK)
                .build();
    }
}
