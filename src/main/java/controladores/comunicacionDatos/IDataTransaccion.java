package controladores.comunicacionDatos;

import modelo.datos.entidades.Transaccion;

/**
 * Created by Demils on 05/06/2016.
 */
public interface IDataTransaccion {
    Transaccion getTransaccion(int idTransaccion);
}
