package modelo.persistencia;

import controladores.comunicacionDatos.IDataConversacion;
import modelo.datos.entidades.Conversacion;
import modelo.datos.entidades.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by David on 02/06/2016.
 */
@Stateless
public class JpaConversacion implements IDataConversacion {
    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public Conversacion getByUsers(Usuario usuario, Usuario otroUser) {
        //Conversacion.findByPeople
        TypedQuery<Conversacion> query = mEntityManager.createNamedQuery("Conversacion.findByPeople", Conversacion.class);
        query.setParameter("user1", usuario);
        query.setParameter("user2", otroUser);
        List<Conversacion> resultados = query.getResultList();
        return resultados.size() == 0 ? null : resultados.get(0);
    }

    @Override
    public Conversacion create(Usuario usuario, Usuario otroUser) {
        Conversacion conversacion = new Conversacion();
        mEntityManager.persist(conversacion);
        conversacion.setUsuario1(usuario);
        conversacion.setUsuario2(otroUser);
        mEntityManager.flush();
        return conversacion;
    }
}
