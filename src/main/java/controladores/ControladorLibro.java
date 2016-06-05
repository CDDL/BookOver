package controladores;

import controladores.comunicacionDatos.IDataLibro;
import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;
import servicios.comunicacionControlador.IControllerLibro;

import javax.inject.Inject;

/**
 * Created by David on 01/06/2016.
 */

public class ControladorLibro implements IControllerLibro {
    @Inject
    private IDataLibro mDataLibro;

    @Override
    public void registrarLibro(Libro libro) {
        libro.setVisible(true);
        mDataLibro.addLibro(libro);
    }

    @Override
    public Libro getLibro(int idlibro) {
        return mDataLibro.getById(idlibro);
    }

    @Override
    public void editarLibro(int idlibro, Libro libro) {
        Libro miLibro = mDataLibro.getById(idlibro);
        miLibro.setEditorial(libro.getEditorial());
        miLibro.setAutor(libro.getAutor());
        miLibro.setUsuario(libro.getUsuario());
        miLibro.setEsPrestable(libro.getEsPrestable());
        miLibro.setEsVendible(libro.getEsVendible());
        miLibro.setEsIntercambiable(libro.getEsIntercambiable());
        miLibro.setEstado(libro.getEstado());
        miLibro.setFotos(libro.getFotos());
        miLibro.setInfoAdicional(libro.getInfoAdicional());
        miLibro.setIsbn(libro.getIsbn());
        miLibro.setVisible(libro.isVisible());
        miLibro.setPrecio(libro.getPrecio());
        mDataLibro.actualizar(miLibro);

    }

    @Override
    public void retirarLibro(int idlibro) {
        Libro miLibro = mDataLibro.getById(idlibro);
        miLibro.setVisible(false);
    }

    @Override
    public Usuario getUserFromLibro(Libro libro) {
        return libro.getUsuario();
    }

    @Override
    public boolean existeLibroId(int idLibro) {
        return mDataLibro.getById(idLibro) != null;
    }

    @Override
    public Libro[] getLibrosUser(Usuario usuario) {

        return mDataLibro.getLibros(usuario);
    }
}
