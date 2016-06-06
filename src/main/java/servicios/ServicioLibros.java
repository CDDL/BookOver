package servicios;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataProfileUser;
import servicios.comunicacionControlador.IControllerLibro;
import servicios.comunicacionControlador.IControllerToken;
import servicios.comunicacionControlador.IControllerUsuario;

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

    @Inject
    IControllerUsuario mUserController;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response registrarLibro(@HeaderParam("Authentication") String token, Libro libro) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        libro.setUsuario(mTokenController.getUserFromToken(token));
        mLibroController.registrarLibro(libro);

        return status(OK)
                .entity(libro.getId())
                .build();
    }

    @PUT
    @Path("{idLibro}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editarLibro(@HeaderParam("Authentication") String token,@PathParam("idLibro") int idlibro, Libro libro){
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario = mTokenController.getUserFromToken(token);
        Libro miLibro = mLibroController.getLibro(idlibro);
        if(miLibro==null) return status(NOT_FOUND).build();
        if(!miLibro.getUsuario().equals(usuario)) return status(FORBIDDEN).build();
        mLibroController.editarLibro(idlibro,libro);

        return status(OK)
                .build();

    }

    @DELETE
    @Path("{idLibro}")
    public Response retirarLibro(@HeaderParam("Authentication") String token,@PathParam("idLibro") int idlibro){
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario = mTokenController.getUserFromToken(token);
        Libro miLibro = mLibroController.getLibro(idlibro);
        if(miLibro==null) return status(NOT_FOUND).build();
        if(!miLibro.getUsuario().equals(usuario)) return status(FORBIDDEN).build();
        mLibroController.retirarLibro(idlibro);

        return status(OK)
                .build();
    }

    @GET
    @Path("lista/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response visualizarLibro(@HeaderParam("Authentication") String token,@PathParam("idUsuario") String idUsuario) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
       Usuario usuario;
        if(idUsuario==null){
            usuario = mTokenController.getUserFromToken(token);
        }else{
            usuario = mUserController.getUserById(Integer.parseInt(idUsuario));
        }

        if(usuario==null) return status(NOT_FOUND).build();
        Libro[] libros = mLibroController.getLibrosUser(usuario);
        return status(OK).entity(libros).build();
    }

    @GET
    @Path("lista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response visualizarLibro(@HeaderParam("Authentication") String token) {
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Usuario usuario;
        usuario = mTokenController.getUserFromToken(token);


        if(usuario==null) return status(NOT_FOUND).build();
        Libro[] libros = mLibroController.getLibrosUser(usuario);
        return status(OK).entity(libros).build();
    }

    @GET
    @Path("buscar/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarLibro(@HeaderParam("Authentication") String token, @PathParam("query") String query){
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Libro[] libros = mLibroController.getLibrosTitulo(query);
        return status(OK).entity(libros).build();

    }

    @GET
    @Path("user/{idLibro}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response findOwnerLibro(@HeaderParam("Authentication") String token, @PathParam("idLibro") int idLibro){
        if (!mTokenController.existeToken(token)) return status(UNAUTHORIZED).build();
        Libro libro = mLibroController.getLibro(idLibro);
        if(libro==null) return status(NOT_FOUND).build();
        //DataProfileUser data = mUserController.getUserLibro(libro);
        int idusuario = mUserController.getIduserLibro(libro);
        return status(OK).entity(idusuario).build();
    }

}
