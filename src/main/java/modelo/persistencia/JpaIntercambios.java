package modelo.persistencia;

import controladores.comunicacionDatos.IDataIntercambios;
import controladores.comunicacionDatos.IDataVenta;
import modelo.datos.entidades.Intercambio;
import modelo.datos.entidades.Venta;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by Demils on 02/06/2016.
 */

@Stateless
public class JpaIntercambios implements IDataIntercambios {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;


    @Override
    public int addIntercambio(Intercambio intercambio) {
        mEntityManager.persist(intercambio);
        return intercambio.getId();
    }

    @Override
    public Intercambio getIntercambio(int idTransaccion) {
        return mEntityManager.find(Intercambio.class, idTransaccion);
    }
}
