package modelo.servicios;

import modelo.datos.entidades.Libro;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class LibroService {

    @PersistenceContext(unitName = "personasJTA")
    EntityManager entitymanager;

    public Libro add(Libro libro) {

        libro.getUsuario().getListaLibros().add(libro);

        entitymanager.persist(libro);

        return libro;
    }


    public Libro getById(int id) {

        Libro libro = entitymanager.getReference(Libro.class, id);

        return libro;
    }

    public Libro getByTitulo(String titulo) {

        Libro libro = (Libro) entitymanager.createQuery(
                "SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", titulo)
                .getResultList().get(0);

        return libro;
    }

    public Libro edit(Libro libro) {

        entitymanager.merge(libro);

        return libro;
    }
}
