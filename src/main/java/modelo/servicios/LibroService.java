package modelo.servicios;

import modelo.datos.Libro;
import modelo.datos.Usuario;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Singleton
public class LibroService {

    @PersistenceContext(unitName = "personasJTA")
    EntityManager entitymanager;

    public boolean add(String titulo, String autor, String editorial, String isbn, String estado, String infoAdicional, Boolean esVendible, Boolean esIntercambiable, Boolean esPrestable, Usuario usuario){

        Usuario usuarioAux = (Usuario) entitymanager.createQuery(
                "SELECT u FROM Usuario u WHERE u.id = :id")
                .setParameter("id", usuario.getId())
                .getResultList().get(0);

        if (usuario.getId() == usuarioAux.getId()) {

            Libro libro = new Libro();
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEsIntercambiable(esIntercambiable);
            libro.setEsVendible(esVendible);
            libro.setEsPrestable(esPrestable);
            libro.setIsbn(isbn);
            libro.setEstado(estado);
            libro.setTitulo(titulo);
            libro.setInfoAdicional(infoAdicional);

            usuario.getListaLibros().add(libro);

            entitymanager.persist(libro);

            return true;
        } else{
            return false;
        }
    }


    public Libro getById(int id){

        Libro libro = entitymanager.getReference(Libro.class, id);

        return libro;
    }

    public Libro getByTitulo(String titulo){

        Libro libro = (Libro) entitymanager.createQuery(
                "SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", titulo)
                .getResultList().get(0);

        return libro;
    }

    public boolean edit(String titulo, String autor, String editorial, String isbn, String estado, String infoAdicional, Boolean esVendible, Boolean esIntercambiable, Boolean esPrestable, Usuario usuario, int id) {

        Usuario usuarioAux = (Usuario) entitymanager.createQuery(
                "SELECT u FROM Usuario u WHERE u.id = :id")
                .setParameter("id", usuario.getId())
                .getResultList().get(0);
        if (usuario.getId() == usuarioAux.getId()) {
            Libro libro = getById(id);

            if (libro!=null){
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEsIntercambiable(esIntercambiable);
                libro.setEsVendible(esVendible);
                libro.setEsPrestable(esPrestable);
                libro.setIsbn(isbn);
                libro.setEstado(estado);
                libro.setTitulo(titulo);
                libro.setInfoAdicional(infoAdicional);

//                entitymanager.persist(libro);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
