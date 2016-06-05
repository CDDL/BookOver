package controladores.comunicacionDatos;

import modelo.datos.entidades.Intercambio;

/**
 * Created by Demils on 05/06/2016.
 */
public interface IDataIntercambios {
    int addIntercambio(Intercambio intercambio);

    Intercambio getIntercambio(int idTransaccion);
}
