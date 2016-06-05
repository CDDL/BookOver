package servicios.comunicacionControlador;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;

/**
 * Created by David on 01/06/2016.
 */
public interface IControllerLibro{

    void registrarLibro(Libro libro);

    Libro getLibro(int idlibro);

    void editarLibro(int idlibro, Libro libro);

    void retirarLibro(int idlibro);

    Usuario getUserFromLibro(Libro libro);

    boolean existeLibroId(int idLibro);

    Libro[] getLibrosUser(Usuario usuario);

    Libro[] getLibrosTitulo(String query);
}
