package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;
import servicios.comunicacionControlador.IControllerLibro;
import servicios.comunicacionControlador.IControllerToken;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;

/**
 * Created by Demils on 31/05/2016.
 */

@Path("libros")
@Stateless
public class ServicioLibros {

    @Inject
    IControllerToken mTokenController;

    @Inject
    IControllerLibro mLibroController;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response registrarLibro(@HeaderParam("Authentication") String token, Libro libro) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        libro.setUsuario(mTokenController.getUserFromToken(token));
        mLibroController.registrarLibro(libro);

        return status(OK).entity(libro.getId())
                .build();
    }

    @PUT
    @Path("{idLibro}")
    public Response editarLibro(@HeaderParam("Authentication") String token,@PathParam("idLibro") int idlibro, Libro libro){
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario = mTokenController.getUserFromToken(token);
        Libro miLibro = mLibroController.getLibro(idlibro);
        if(miLibro==null){
            return status(NOT_FOUND).build();
        }
        if(!miLibro.getUsuario().equals(usuario)){
            return status(FORBIDDEN).build();
        }
        mLibroController.editarLibro(idlibro,libro);

        return status(OK).build();

    }

}
