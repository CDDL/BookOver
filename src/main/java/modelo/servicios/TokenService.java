package modelo.servicios;

import modelo.datos.entidades.Token;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Demils on 09/05/2016.
 */
public class TokenService {

    @PersistenceContext(unitName = "personasJTA")
    private EntityManager entitymanager;

    public Token getByToken(String token) {
        return entitymanager.find(Token.class, token);
    }

    public void add(Token token) {
        entitymanager.persist(token);

    }
}
