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

import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.status;

/**
 * Created by Demils on 31/05/2016.
 */

@Path("libros")
@Stateless
public class ServicioLibros {

    @Inject
    private IControllerToken mTokenController;

    @Inject
    IControllerLibro mLibroController;

    @POST
    @Path("{idLibro}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response registrarLibro(@HeaderParam("Authentication") String token,@PathParam("idLibro") int idLibro, Libro libro) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        libro.setUsuario(mTokenController.getUserFromToken(token));
        libro.setId(idLibro);
        mLibroController.registrarLibro(libro);
        return status(OK)
                .build();
    }
}
