package modelo.persistencia;

import controladores.comunicacionDatos.IDataLibro;
import modelo.datos.entidades.Libro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

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
        //return mEntityManager.find(Libro.class,id);
        TypedQuery<Libro> query = mEntityManager.createNamedQuery("Libro.getId", Libro.class);
        List<Libro> resultados = query.getResultList();
        return resultados.size() == 0 ? null : resultados.get(0);
    }
}
