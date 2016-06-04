package controladores.comunicacionDatos;

import modelo.datos.entidades.Prestamo;
import modelo.datos.entidades.Transaccion;

/**
 * Created by Demils on 02/06/2016.
 */
public interface IDataPrestamo {
    int addPrestamo(Prestamo prestamo);

    Prestamo getPrestamo(int idTransaccion);

    void actualizar(Transaccion transaccionPrestamo);
}
