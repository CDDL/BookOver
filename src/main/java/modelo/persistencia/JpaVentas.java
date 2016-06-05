package modelo.persistencia;

import controladores.comunicacionDatos.IDataPrestamo;
import controladores.comunicacionDatos.IDataVenta;
import modelo.datos.entidades.Prestamo;
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
public class JpaVentas implements IDataVenta {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public int addVenta(Venta venta) {
        mEntityManager.persist(venta);
        return venta.getId();
    }

    @Override
    public Venta getVenta(int idTransaccion) {
        return mEntityManager.find(Venta.class, idTransaccion);
    }

}
