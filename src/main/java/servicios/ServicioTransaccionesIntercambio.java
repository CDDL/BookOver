package servicios;

import modelo.datos.entidades.Intercambio;
import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Prestamo;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataIntercambio;
import servicios.comunicacionControlador.IControllerLibro;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerTransaccion;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;

/**
 * Created by Demils on 02/06/2016.
 */

@Path("transacciones/intercambio")
public class ServicioTransaccionesIntercambio {

    @Inject
    IControllerTransaccion mControllerTransaccion;

    @Inject
    IControllerLibro mControllerLibro;

    @Inject
    IControllerToken mControllerToken;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response solicitarIntercambio(@HeaderParam("Authentication") String token, DataIntercambio dataIntercambio) {
        Libro libroDelSolicitante = mControllerLibro.getLibro(dataIntercambio.getLibroSolicitante());
        Libro libroDelSolicitado = mControllerLibro.getLibro(dataIntercambio.getLibroSolicitado());
        Usuario usuarioSolicitante = mControllerToken.getUserFromToken(token);


        if(usuarioSolicitante == null) return status(UNAUTHORIZED).build();
        if(libroDelSolicitante == null || libroDelSolicitado == null) return status(NOT_FOUND).build();
        if (!libroDelSolicitante.getUsuario().equals(usuarioSolicitante)) return status(FORBIDDEN).build();
        if (!(libroDelSolicitante.getEsIntercambiable() && libroDelSolicitado.getEsIntercambiable())) return status(CONFLICT).build();

        int idTransaccion = mControllerTransaccion.nuevaSolicitudIntercambio(usuarioSolicitante, libroDelSolicitante, libroDelSolicitado);

        return status(OK)
                .entity(idTransaccion)
                .build();
    }

    @Path("realizado/{idTransaccion}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response confirmarIntercambio(@HeaderParam("Authentication") String token, @PathParam("idTransaccion") int idTransaccion) {
        Usuario usuarioSolicitado = mControllerToken.getUserFromToken(token);
        Intercambio transaccionIntercambio = mControllerTransaccion.getIntercambio(idTransaccion);

        if(usuarioSolicitado == null) return status(UNAUTHORIZED).build();
        if(transaccionIntercambio == null) return status(NOT_FOUND).build();
        if(!transaccionIntercambio.getUsuarioRecibeTransaccion().equals(usuarioSolicitado)) return status(FORBIDDEN).build();
        if(transaccionIntercambio.getIntercambioRealizado()) return status(CONFLICT).build();

        transaccionIntercambio.setIntercambioRealizado(true);
        mControllerTransaccion.actualizar(transaccionIntercambio);

        return status(OK)
                .build();
    }
}
