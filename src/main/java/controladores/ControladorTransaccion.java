package controladores;

import controladores.comunicacionDatos.*;
import modelo.datos.entidades.*;
import modelo.datos.transferencia.DataTransacciones;
import servicios.comunicacionControlador.IControllerTransaccion;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

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

    @Inject
    private IDataIntercambios mDataIntercambios;

    @Inject
    private IDataValoraciones mDataValoraciones;

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

    @Override
    public int nuevaSolicitudIntercambio(Usuario usuarioSolicitante, Libro libroDelSolicitante, Libro libroDelSolicitado) {
        Intercambio intercambio = new Intercambio();
        intercambio.setUsuarioIniciaTransaccion(usuarioSolicitante);
        intercambio.setUsuarioRecibeTransaccion(libroDelSolicitado.getUsuario());
        intercambio.setLibroOfrecido(libroDelSolicitante);
        intercambio.setLibroBuscado(libroDelSolicitado);
        intercambio.setAceptada(false);
        intercambio.setIntercambioRealizado(false);
        return mDataIntercambios.addIntercambio(intercambio);
    }

    @Override
    public Intercambio getIntercambio(int idTransaccion) {
        return mDataIntercambios.getIntercambio(idTransaccion);
    }

    @Override
    public void addValoracion(Usuario usuario, int idTransaccion, Valoracion valoracion) {
        Transaccion transaccion = mDataTransaccion.getTransaccion(idTransaccion);
        valoracion.setTransaccion(transaccion);
        valoracion.setUsuarioRealiza(usuario);
        valoracion.setUsuarioValorado(transaccion.getUsuarioIniciaTransaccion().equals(usuario) ? transaccion.getUsuarioRecibeTransaccion() : transaccion.getUsuarioRecibeTransaccion());
        mDataValoraciones.addValoracion(valoracion);
    }

    @Override
    public List<DataTransacciones> getListaTransacciones(Usuario usuarioSolicitado) {
        List<Transaccion> listaTransaccionesRecibidas = usuarioSolicitado.getListaTransaccionesRecibidas();
        List<Transaccion> listaTransaccionesRealizadas = usuarioSolicitado.getListaTransaccionesIniciadas();

        List<DataTransacciones> listaTransaccioneFormateadas = new LinkedList<>();

        for (Transaccion transaccion : listaTransaccionesRealizadas) {
            DataTransacciones dataTransacciones = new DataTransacciones();
            dataTransacciones.setId(transaccion.getId());
            dataTransacciones.setTipoTransaccion(getTipoTransaccionRealizada(transaccion));
            dataTransacciones.setConQuien(transaccion.getUsuarioRecibeTransaccion().getUsername());
            listaTransaccioneFormateadas.add(dataTransacciones);
        }

        for (Transaccion transaccion : listaTransaccionesRecibidas) {
            DataTransacciones dataTransacciones = new DataTransacciones();
            dataTransacciones.setId(transaccion.getId());
            dataTransacciones.setTipoTransaccion(getTipoTransaccionRecibida(transaccion));
            dataTransacciones.setConQuien(transaccion.getUsuarioIniciaTransaccion().getUsername());
            listaTransaccioneFormateadas.add(dataTransacciones);
        }

        return null;
    }

    private int getTipoTransaccionRealizada(Transaccion transaccion) {
        if (transaccion instanceof Prestamo) {
            Prestamo prestamo = (Prestamo) transaccion;
            if (prestamo.getLibroRecibido()) return Transaccion.TIPO_TRANSACCION_PRESTAR;
        }

        if (transaccion instanceof Venta) {
            Venta venta = (Venta) transaccion;
            if (venta.getConfirmacion()) return Transaccion.TIPO_TRANSACCION_VENTA;

        }
        if (transaccion instanceof Intercambio) {
            Intercambio intercambio = (Intercambio) transaccion;
            if (intercambio.getIntercambioRealizado()) return Transaccion.TIPO_TRANSACCION_INTERCAMBIO;

        }
        return -1;
    }

    private int getTipoTransaccionRecibida(Transaccion transaccion) {
        if (transaccion instanceof Prestamo) {
            Prestamo prestamo = (Prestamo) transaccion;
            if (prestamo.getLibroDevuelto()) return Transaccion.TIPO_TRANSACCION_DEVOLVER_PRESTAMO;
        }

        if (transaccion instanceof Venta) {
            Venta venta = (Venta) transaccion;
            if (venta.getConfirmacion()) return Transaccion.TIPO_TRANSACCION_COMPRA;

        }
        if (transaccion instanceof Intercambio) {
            Intercambio intercambio = (Intercambio) transaccion;
            if (intercambio.getIntercambioRealizado()) return Transaccion.TIPO_TRANSACCION_INTERCAMBIO;
        }
        return -1;

    }

}
