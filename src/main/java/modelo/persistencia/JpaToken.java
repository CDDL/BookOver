package modelo.persistencia;

import controladores.comunicacionDatos.IDataToken;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Demils on 09/05/2016.
 */
@Stateless
public class JpaToken implements IDataToken {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public Token guardarToken(Token token) {
        mEntityManager.persist(token);
        return token;
    }

    @Override
    public Token getByToken(String token) {
        TypedQuery<Token> query = mEntityManager.createNamedQuery("Token.getByToken", Token.class);
        query.setParameter("token", token);

        List<Token> resultados = query.getResultList();
        return resultados.size() == 0 ? null : resultados.get(0);
    }

    @Override
    public void test() {
        Token token = new Token();
        mEntityManager.persist(token);
    }

    @Override
    public Usuario getUserFromToken(Token token) {
        return null;
    }
}
