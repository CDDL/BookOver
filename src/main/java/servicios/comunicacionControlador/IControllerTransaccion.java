package servicios.comunicacionControlador;

import modelo.datos.entidades.*;

/**
 * Created by Demils on 02/06/2016.
 */
public interface IControllerTransaccion {

    Prestamo getPrestamo(int idTransaccion);

    int nuevaSolicitudPrestamos(Usuario usuarioQueSolicita, Libro libroSolicitado);

    void actualizar(Transaccion transaccionPrestamo);

    int nuevaSolicitudCompra(Usuario usuarioQueSolicita, Libro libroSolicitado);

    Venta getVenta(int idTransaccion);

    Transaccion getTransaccion(int idTransaccion);
}
