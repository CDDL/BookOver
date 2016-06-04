package modelo.persistencia;

import controladores.comunicacionDatos.IDataPrestamo;
import modelo.datos.entidades.Prestamo;
import modelo.datos.entidades.Transaccion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by Demils on 02/06/2016.
 */

@Stateless
public class JpaPrestamos implements IDataPrestamo {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public int addPrestamo(Prestamo prestamo) {
        mEntityManager.persist(prestamo);
        return prestamo.getId();
    }

    @Override
    public Prestamo getPrestamo(int idTransaccion) {
        return mEntityManager.find(Prestamo.class, idTransaccion);
    }

    @Override
    public void actualizar(Transaccion transaccionPrestamo) {
        mEntityManager.merge(transaccionPrestamo);
    }
}
