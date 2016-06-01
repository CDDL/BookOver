package modelo.persistencia;

import controladores.comunicacionDatos.IDataLibro;
import modelo.datos.entidades.Libro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by David on 01/06/2016.
 */
public class JpaLibro implements IDataLibro {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public void addLibro(Libro libro) {
        mEntityManager.persist(libro);
    }

    @Override
    public Libro getById(int id) {
        return mEntityManager.find(Libro.class,id);
    }
}
