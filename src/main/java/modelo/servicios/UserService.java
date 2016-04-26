package modelo.servicios;

import modelo.datos.Usuario;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Created by David on 11/04/2016.
 */
@Singleton
public class UserService {

    @PersistenceContext(unitName = "personasJTA")
    EntityManager entitymanager;


    public Usuario add(String password, String email, String ubicacion, String username ){
        Usuario usuario = new Usuario();

        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setUbicacion(ubicacion);
        usuario.setUsername(username);

        entitymanager.persist(usuario);

        return usuario;


    }

    public Usuario getById(int id){

        Usuario usuario = entitymanager.getReference(Usuario.class, id);

        return usuario;
    }

    public Usuario getByEmail(String email){

        Usuario usuario = (Usuario) entitymanager.createQuery(
                "SELECT u FROM Usuario u WHERE u.email LIKE :email")
                .setParameter("email", email)
                .getResultList().get(0);
        return usuario;
    }

    public Usuario getByUsername(String username){

        Usuario usuario = (Usuario) entitymanager.createQuery(
                "SELECT u FROM Usuario u WHERE u.username LIKE :username")
                .setParameter("username", username)
                .getResultList().get(0);
        return usuario;
    }






}
