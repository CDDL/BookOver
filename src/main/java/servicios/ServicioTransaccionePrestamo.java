package servicios;

import com.sun.media.sound.SoftTuning;
import modelo.datos.entidades.Libro;
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
import static javax.ws.rs.core.Response.status;

/**
 * Created by Demils on 02/06/2016.
 */

@Path("transacciones/prestamo")
public class ServicioTransaccionePrestamo {

    @Inject
    IControllerTransaccion mControllerTransaccion;

    @Inject
    IControllerLibro mControllerLibro;

    @Inject
    IControllerToken mControllerToken;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response solicitarPrestamos(@HeaderParam("Authentication") String token, Libro libro) {
        Libro libroSolicitado = mControllerLibro.getLibro(libro.getId());
        Usuario usuarioQueSolicita = mControllerToken.getUserFromToken(token);

        if(usuarioQueSolicita == null) return status(UNAUTHORIZED).build();
        if(libroSolicitado == null) return status(NOT_FOUND).build();
        if(!libroSolicitado.getEsPrestable()) return status(CONFLICT).build();

        int idTransaccion = mControllerTransaccion.nuevaSolicitudPrestamos(usuarioQueSolicita, libroSolicitado);

        return status(OK)
                .entity(idTransaccion)
                .build();
    }

    @Path("recibido/{idTransaccion}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response confirmarRecibido(@HeaderParam("Authentication") String token, @PathParam("idTransaccion") int idTransaccion) {
        Usuario usuarioAlQueSePresta = mControllerToken.getUserFromToken(token);
        Prestamo transaccionPrestamo = mControllerTransaccion.getPrestamo(idTransaccion);

        if(usuarioAlQueSePresta == null) return status(UNAUTHORIZED).build();
        if(transaccionPrestamo == null) return status(NOT_FOUND).build();
        if(!transaccionPrestamo.getUsuarioIniciaTransaccion().equals(usuarioAlQueSePresta)) status(FORBIDDEN).build();
        if(transaccionPrestamo.getLibroRecibido()) return status(CONFLICT).build();

        transaccionPrestamo.setLibroRecibido(true);
        mControllerTransaccion.actualizar(transaccionPrestamo);

        return status(OK)
                .build();
    }

    @Path("devuelto/{idTransaccion}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response confirmarDevuelto(@HeaderParam("Authentication") String token, @PathParam("idTransaccion") int idTransaccion) {
        Usuario usuarioQuePresta = mControllerToken.getUserFromToken(token);
        Prestamo transaccionPrestamo = mControllerTransaccion.getPrestamo(idTransaccion);

        if(usuarioQuePresta == null) return status(UNAUTHORIZED).build();
        if(transaccionPrestamo == null) return status(NOT_FOUND).build();
        if(!transaccionPrestamo.getUsuarioRecibeTransaccion().equals(usuarioQuePresta)) return status(FORBIDDEN).build();
        System.out.println(transaccionPrestamo.getLibroDevuelto());
        System.out.println(transaccionPrestamo.getAceptada());
        if(transaccionPrestamo.getLibroDevuelto()) return status(CONFLICT).build();
        if(!transaccionPrestamo.getAceptada()) return status(CONFLICT).build();

        transaccionPrestamo.setLibroDevuelto(true);
        mControllerTransaccion.actualizar(transaccionPrestamo);

        return status(OK)
                .build();
    }

}
