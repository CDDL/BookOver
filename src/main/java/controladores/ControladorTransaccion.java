package controladores;

import controladores.comunicacionDatos.IDataPrestamo;
import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Prestamo;
import modelo.datos.entidades.Transaccion;
import modelo.datos.entidades.Usuario;
import servicios.comunicacionControlador.IControllerTransaccion;

import javax.inject.Inject;

/**
 * Created by Demils on 02/06/2016.
 */
public class ControladorTransaccion implements IControllerTransaccion {

    @Inject
    private IDataPrestamo mDataPrestamos;

    @Override
    public Prestamo getPrestamo(int idTransaccion) {
        return mDataPrestamos.getPrestamo(idTransaccion);
    }

    @Override
    public int nuevaSolicitudPrestamos(Usuario usuarioQueSolicita, Libro libroSolicitado) {
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuarioIniciaTransaccion(usuarioQueSolicita);
        prestamo.setUsuarioRecibeTransaccion(libroSolicitado.getUsuario());
        prestamo.setLibro(libroSolicitado);
        prestamo.setAceptada(false);
        prestamo.setLibroDevuelto(false);
        prestamo.setLibroRecibido(false);
        return mDataPrestamos.addPrestamo(prestamo);
    }

    @Override
    public void actualizar(Transaccion transaccionPrestamo) {
        mDataPrestamos.actualizar(transaccionPrestamo);
    }
}
