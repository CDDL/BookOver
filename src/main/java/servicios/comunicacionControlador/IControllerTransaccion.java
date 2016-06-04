package servicios.comunicacionControlador;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Prestamo;
import modelo.datos.entidades.Usuario;

/**
 * Created by Demils on 02/06/2016.
 */
public interface IControllerTransaccion {

    Prestamo getPrestamo(int idTransaccion);

    int nuevaSolicitudPrestamos(Usuario usuarioQueSolicita, Libro libroSolicitado);

    void actualizar(Prestamo transaccionPrestamo);
}
