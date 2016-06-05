package controladores;

import controladores.comunicacionDatos.IDataPrestamo;
import controladores.comunicacionDatos.IDataTransaccion;
import controladores.comunicacionDatos.IDataVenta;
import modelo.datos.entidades.*;
import servicios.comunicacionControlador.IControllerTransaccion;

import javax.inject.Inject;

/**
 * Created by Demils on 02/06/2016.
 */
public class ControladorTransaccion implements IControllerTransaccion {

    @Inject
    private IDataPrestamo mDataPrestamos;

    @Inject
    private IDataVenta mDataVentas;

    @Inject
    private IDataTransaccion mDataTransaccion;

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

    @Override
    public int nuevaSolicitudCompra(Usuario usuarioQueSolicita, Libro libroSolicitado) {
        Venta venta = new Venta();
        venta.setUsuarioIniciaTransaccion(usuarioQueSolicita);
        venta.setUsuarioRecibeTransaccion(libroSolicitado.getUsuario());
        venta.setLibro(libroSolicitado);
        venta.setAceptada(false);
        venta.setLibroVendido(false);
        return mDataVentas.addVenta(venta);
    }

    @Override
    public Venta getVenta(int idTransaccion) {
        return mDataVentas.getVenta(idTransaccion);
    }

    @Override
    public Transaccion getTransaccion(int idTransaccion) {
        return mDataTransaccion.getTransaccion(idTransaccion);
    }

}
