package modelo.persistencia;

import controladores.comunicacionDatos.IDataUsuario;
import modelo.datos.entidades.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by David on 11/04/2016.
 */

@Stateless
public class JpaUsuario implements IDataUsuario {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public Usuario getByUsername(String username) {
        TypedQuery<Usuario> query = mEntityManager.createNamedQuery("Usuario.getByUsername", Usuario.class);
        query.setParameter("username", username);

        List<Usuario> resultados = query.getResultList();
        return resultados.size() == 0 ? null : resultados.get(0);
    }

    @Override
    public void addUsuario(Usuario usuario) {
        mEntityManager.persist(usuario);
    }

    @Override
    public Usuario getById(int id) {
        return mEntityManager.find(Usuario.class, id);
    }
}
