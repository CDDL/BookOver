package modelo.persistencia;

import controladores.comunicacionDatos.IDataLibro;
import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;

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
        query.setParameter("id", id);
        List<Libro> resultados = query.getResultList();
        return resultados.size() == 0 ? null : resultados.get(0);
    }

    @Override
    public void actualizar(Libro libro) {
        mEntityManager.merge(libro);
    }

    @Override
    public Libro[] getLibros(Usuario usuario) {
        TypedQuery<Libro> query = mEntityManager.createNamedQuery("Libro.getLista", Libro.class);
        query.setParameter("user", usuario);
        List<Libro> resultados = query.getResultList();
        Libro[] resultado = new Libro[resultados.size()];
        resultado = resultados.toArray(resultado);
        return resultado;
    }

    @Override
    public Libro[] getLibrosTitulo(String titulo) {
        //Libro.getByTitulo
        TypedQuery<Libro> query = mEntityManager.createNamedQuery("Libro.getByTitulo", Libro.class);
        query.setParameter("titulo", "%" + titulo + "%");
        List<Libro> resultados = query.getResultList();
        Libro[] resultado = new Libro[resultados.size()];
        resultado = resultados.toArray(resultado);
        return resultado;
    }
}
