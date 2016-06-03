package modelo.persistencia;

import controladores.comunicacionDatos.IDataUsuario;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListUser;

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

    @Override
    public DataListUser[] listaPersonas(Usuario usuario) {
        //Usuario.getAllWithoutMe
        TypedQuery<Usuario> query = mEntityManager.createNamedQuery("Usuario.getAllWithoutMe", Usuario.class);
        query.setParameter("user", usuario);
        List<Usuario> resultados = query.getResultList();
        DataListUser[] miResult = new DataListUser[resultados.size()];
        int contador = 0;
        for(Usuario user : resultados){
            DataListUser user_formatted = new DataListUser();
            user_formatted.setId(user.getId());
            user_formatted.setNick(user.getUsername());
            user_formatted.setEmail(user.getEmail());
            miResult[contador] = user_formatted;
            contador++;
        }

        return miResult;
    }
}
