package modelo.servicios;

import modelo.datos.Libro;
import modelo.datos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by David on 11/04/2016.
 */
public class LibroService {

    public void add(String titulo, String autor, String editorial, String isbn, String estado, String infoAdicional, Boolean esVendible, Boolean esIntercambiable, Boolean esPrestable, Usuario usuario){

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "bookover" );
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Libro libro = new Libro();
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setEsIntercambiable(esIntercambiable);
        libro.setEsVendible(esVendible);
        libro.setEsIntercambiable(esIntercambiable);
        libro.setIsbn(isbn);
        libro.setEstado(estado);
        libro.setTitulo(titulo);
        libro.setInfoAdicional(infoAdicional);

        libro.setUsuario(usuario);
        usuario.getListaLibros().add(libro);

        entitymanager.persist(libro);



        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();

    }



    public Libro getById(int id){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "bookover" );
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Libro libro = entitymanager.getReference(Libro.class, id);

        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();
        return libro;
    }

    public Libro getByTitulo(String titulo){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "bookover" );
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Libro libro = (Libro) entitymanager.createQuery(
                "SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", titulo)
                .getResultList().get(0);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();
        return libro;
    }


}
