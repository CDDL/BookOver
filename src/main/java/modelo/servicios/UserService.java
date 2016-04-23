package modelo.servicios;

import com.sun.jersey.spi.resource.Singleton;
import modelo.datos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by David on 11/04/2016.
 */
@Singleton
public class UserService {


    public void add(String nombre, String apellidos, String password, String email, String ubicacion, String username ){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "bookover" );
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setUbicacion(ubicacion);
        usuario.setUsername(username);

        entitymanager.persist(usuario);

        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();


    }

    public Usuario getById(int id){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "bookover" );
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Usuario usuario = entitymanager.getReference(Usuario.class, id);

        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();
        return usuario;
    }

    public Usuario getByEmail(String email){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "bookover" );
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Usuario usuario = (Usuario) entitymanager.createQuery(
                "SELECT u FROM Usuario u WHERE u.email LIKE :email")
                .setParameter("email", email)
                .getResultList().get(0);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();
        return usuario;
    }






}
