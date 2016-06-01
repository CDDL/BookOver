package controladores;

import controladores.comunicacionDatos.IDataLibro;
import modelo.datos.entidades.Libro;
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
            mDataLibro.addLibro(libro);
    }
}
