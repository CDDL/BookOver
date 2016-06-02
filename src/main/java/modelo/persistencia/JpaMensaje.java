package modelo.persistencia;

import controladores.comunicacionDatos.IDataMensaje;
import modelo.datos.entidades.Conversacion;
import modelo.datos.entidades.Mensaje;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataMensaje;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Date;

/**
 * Created by David on 02/06/2016.
 */
@Stateless
public class JpaMensaje implements IDataMensaje{

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public Mensaje sendMessage(Conversacion conversacion, DataMensaje dataMensaje, Usuario usuario) {
        Mensaje mensaje = new Mensaje();
        mEntityManager.persist(mensaje);
        mensaje.setUsuario(usuario);
        mensaje.setMensaje(dataMensaje.getMensaje());
        mensaje.setFecha(new Date());
        mensaje.setConversacion(conversacion);
        return mensaje;
    }
}
