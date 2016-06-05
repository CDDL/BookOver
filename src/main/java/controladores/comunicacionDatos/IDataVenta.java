package controladores.comunicacionDatos;

import modelo.datos.entidades.Venta;

/**
 * Created by Demils on 05/06/2016.
 */
public interface IDataVenta {
    int addVenta(Venta venta);

    Venta getVenta(int idTransaccion);
}
