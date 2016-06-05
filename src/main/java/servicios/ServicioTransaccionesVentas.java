package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Prestamo;
import modelo.datos.entidades.Usuario;
import modelo.datos.entidades.Venta;
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

@Path("transacciones/compra")
public class ServicioTransaccionesVentas {

    @Inject
    IControllerTransaccion mControllerTransaccion;

    @Inject
    IControllerLibro mControllerLibro;

    @Inject
    IControllerToken mControllerToken;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response solicitarCompra(@HeaderParam("Authentication") String token, Libro libro) {
        Libro libroSolicitado = mControllerLibro.getLibro(libro.getId());
        Usuario usuarioQueSolicita = mControllerToken.getUserFromToken(token);

        if(usuarioQueSolicita == null) return status(UNAUTHORIZED).build();
        if(libroSolicitado == null) return status(NOT_FOUND).build();
        if(!libroSolicitado.getEsVendible()) return status(CONFLICT).build();

        int idTransaccion = mControllerTransaccion.nuevaSolicitudCompra(usuarioQueSolicita, libroSolicitado);

        return status(OK)
                .entity(idTransaccion)
                .build();
    }

    @Path("realizada/{idTransaccion}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response solicitarCompra(@HeaderParam("Authentication") String token, @PathParam("idTransaccion") int idTransaccion) {
        Usuario usuarioQueVende = mControllerToken.getUserFromToken(token);
        Venta transaccionVenta = mControllerTransaccion.getVenta(idTransaccion);

        if(usuarioQueVende == null) return status(UNAUTHORIZED).build();
        if(transaccionVenta == null) return status(NOT_FOUND).build();
        if(!transaccionVenta.getUsuarioRecibeTransaccion().equals(usuarioQueVende)) return status(FORBIDDEN).build();
        if(!transaccionVenta.getAceptada()) return status(CONFLICT).build();
        if(transaccionVenta.getLibroVendido()) return status(CONFLICT).build();

        transaccionVenta.setLibroVendido(true);
        mControllerTransaccion.actualizar(transaccionVenta);

        return status(OK)
                .build();
    }


}
