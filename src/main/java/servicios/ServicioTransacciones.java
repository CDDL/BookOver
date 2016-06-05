package servicios;

import modelo.datos.entidades.Prestamo;
import modelo.datos.entidades.Transaccion;
import modelo.datos.entidades.Usuario;
import modelo.datos.entidades.Valoracion;
import modelo.datos.transferencia.DataTransacciones;
import servicios.comunicacionControlador.IControllerLibro;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerTransaccion;
import servicios.comunicacionControlador.IControllerUsuario;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

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

    @Inject
    IControllerUsuario mControllerUsuario;

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

    @Path("valorar/{idTransaccion}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response valorarTransaccion(@HeaderParam("Authentication") String token, @PathParam("idTransaccion") int idTransaccion, Valoracion valoracion) {
        Usuario usuario = mControllerToken.getUserFromToken(token);
        Transaccion transaccion = mControllerTransaccion.getTransaccion(idTransaccion);

        if(usuario == null) return status(UNAUTHORIZED).build();
        if(transaccion == null) return status(NOT_FOUND).build();
        if(!transaccion.getUsuarioRecibeTransaccion().equals(usuario) && !transaccion.getUsuarioIniciaTransaccion().equals(usuario)) return status(FORBIDDEN).build();
        if(!transaccion.getAceptada()) return status(CONFLICT).build();

        mControllerTransaccion.addValoracion(idTransaccion, valoracion);

        return status(OK)
                .build();
    }

    @Path("listaTransacciones/{idUsuario}}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listaTransacciones(@HeaderParam("Authentication") String token, @PathParam("idUsuario") int idUsuario) {
        Usuario usuarioIdentificado = mControllerToken.getUserFromToken(token);
        Usuario usuarioSolicitado = mControllerUsuario.getUserById(idUsuario);

        if(usuarioIdentificado == null) return status(UNAUTHORIZED).build();
        if(usuarioSolicitado == null) return status(NOT_FOUND).build();

        List<DataTransacciones> listaTransaccioens = mControllerTransaccion.getListaTransacciones(usuarioSolicitado);

        return status(OK)
                .entity(listaTransaccioens)
                .build();
    }

    @Path("listaTransacciones")
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listaTransaccionesPropias(@HeaderParam("Authentication") String token) {
        Usuario usuarioIdentificado = mControllerToken.getUserFromToken(token);

        if(usuarioIdentificado == null) return status(UNAUTHORIZED).build();

        List<DataTransacciones> listaTransaccioens = mControllerTransaccion.getListaTransacciones(usuarioIdentificado);

        return status(OK)
                .entity(listaTransaccioens)
                .build();
    }
}
