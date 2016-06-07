package modelo.persistencia;

import controladores.comunicacionDatos.IDataConversacion;
import modelo.datos.entidades.Conversacion;
import modelo.datos.entidades.Mensaje;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataConversacion;
import modelo.datos.transferencia.DataListConversaciones;

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

    @Override
    public DataListConversaciones[] getAllByUser(Usuario usuario) {
        //Conversacion.findByUser
        TypedQuery<Conversacion> query = mEntityManager.createNamedQuery("Conversacion.findByUser", Conversacion.class);
        query.setParameter("user", usuario);
        List<Conversacion> resultados = query.getResultList();
        DataListConversaciones[] resultado = new DataListConversaciones[resultados.size()];
        int contador = 0;
        for(Conversacion conversa : resultados){
            DataListConversaciones data = new DataListConversaciones();
            data.setId(conversa.getId());
            if(conversa.getUsuario1().getId()!=usuario.getId()){
                data.setConQuien(conversa.getUsuario1().getUsername());
                data.setIdPersona(conversa.getUsuario1().getId());
            }else{
                data.setConQuien(conversa.getUsuario2().getUsername());
                data.setIdPersona(conversa.getUsuario2().getId());
            }

            resultado[contador] = data;
            contador++;
        }

        return resultado;
    }

    @Override
    public boolean participaUser(Usuario usuario, int idConversacion) {
        TypedQuery<Conversacion> query = mEntityManager.createNamedQuery("Conversacion.findByUserAndId", Conversacion.class);
        query.setParameter("id",idConversacion);
        query.setParameter("user", usuario);
        List<Conversacion> resultados = query.getResultList();
        return resultados.size() > 0;
    }

    @Override
    public DataConversacion[] getMensajes(int idConversacion) {
        TypedQuery<Mensaje> query = mEntityManager.createNamedQuery("Mensaje.findByConversa", Mensaje.class);
        query.setParameter("id", idConversacion);
        List<Mensaje> resultados = query.getResultList();
        DataConversacion[] resultado = new DataConversacion[resultados.size()];
        int contador = 0;
        for(Mensaje mensaje : resultados){
            DataConversacion data = new DataConversacion();
            data.setId(mensaje.getUsuario().getId());
            data.setQuien(mensaje.getUsuario().getUsername());
            data.setMensaje(mensaje.getMensaje());
            data.setCuando(mensaje.getFecha());
            resultado[contador] = data;
            contador++;
        }

        return resultado;
    }
}
