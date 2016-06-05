package modelo.persistencia;

import controladores.comunicacionDatos.IDataTransaccion;
import controladores.comunicacionDatos.IDataVenta;
import modelo.datos.entidades.Transaccion;
import modelo.datos.entidades.Venta;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by Demils on 02/06/2016.
 */

@Stateless
public class JpaTransacciones implements IDataTransaccion {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public Transaccion getTransaccion(int idTransaccion) {
        return mEntityManager.find(Transaccion.class, idTransaccion);
    }

}
