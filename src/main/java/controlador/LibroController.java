
package controlador;


import modelo.datos.Libro;
import modelo.datos.Usuario;
import modelo.servicios.LibroService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by Elite Legend on 27/04/2016.
 */

@Path("libro")
public class LibroController {

    @Inject
    LibroService libroService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("registrar")
    public Response registrarLibro(Libro libro) {


        //libro.setUsuario(usuario);

        libroService.add(libro);

        if (libro.getId()==-1){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(libro).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //TODO más xD
    public Response editarLibro(List<Object> parametros) {
        Usuario usuario = (Usuario) parametros.get(0);
        if(usuario==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ////////////////////////////
        Libro requestL = (Libro) parametros.get(1);
        //////////////////////////////

        Libro libro = libroService.getById(requestL.getId());
        libro.setTitulo(requestL.getTitulo());
        libro.setAutor(requestL.getAutor());
        libro.setEditorial(requestL.getEditorial());
        libro.setIsbn(requestL.getIsbn());
        libro.setEstado(requestL.getEstado());
        libro.setInfoAdicional(requestL.getInfoAdicional());
        libro.setEsIntercambiable(requestL.getEsIntercambiable());
        libro.setEsVendible(requestL.getEsVendible());
        libro.setEsPrestable(requestL.getEsPrestable());
        libroService.edit(libro);

        return Response.ok(libro).build();
    }
}
