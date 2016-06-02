package controladores.comunicacionDatos;

import modelo.datos.entidades.Libro;

/**
 * Created by Demils on 16/05/2016.
 */
public interface IDataLibro {
    void addLibro(Libro libro);

    Libro getById(int id);


}
