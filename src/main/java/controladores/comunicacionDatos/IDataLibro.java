package controladores.comunicacionDatos;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;

/**
 * Created by Demils on 16/05/2016.
 */
public interface IDataLibro {
    void addLibro(Libro libro);

    Libro getById(int id);


    void actualizar(Libro miLibro);

    Libro[] getLibros(Usuario usuario);
}
