package modelo.servicios;

import modelo.datos.Usuario;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 * Created by David on 11/04/2016.
 */
@Singleton
public class UserService {

    @PersistenceContext(unitName = "personasJTA")
    EntityManager entitymanager;


    public Usuario add(Usuario usuario){
        entitymanager.persist(usuario);
        return usuario;
    }

    public Usuario getById(int id){
        return entitymanager.getReference(Usuario.class, id);
    }

    public Usuario getByEmail(String email){

        Usuario usuario = (Usuario) entitymanager.createQuery(
                "SELECT u FROM Usuario u WHERE u.email LIKE :email")
                .setParameter("email", email)
                .getResultList().get(0);
        return usuario;
    }

    public Usuario getByUsername(String username){
        Query petición = entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.username LIKE :username")
                .setParameter("username", username);
        try {
            Usuario usuario = (Usuario) petición.getSingleResult();
            return usuario;
        } catch (Exception e){
            return null;
        }
    }

}
