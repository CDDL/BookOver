package controladores;

import controladores.comunicacionDatos.IDataConversacion;
import controladores.comunicacionDatos.IDataMensaje;
import modelo.datos.entidades.Conversacion;
import modelo.datos.entidades.Mensaje;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataMensaje;
import servicios.comunicacionControlador.IControllerMensaje;

import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 * Created by David on 02/06/2016.
 */

public class ControladorMensaje implements IControllerMensaje {

    @Inject
    private IDataMensaje mDataMensaje;

    @Inject
    private IDataConversacion mDataConversacion;

    @Override
    public int enviarMensaje(DataMensaje dataMensaje, Usuario usuario, Usuario otroUser) {
        Conversacion conversacion;
        conversacion = mDataConversacion.getByUsers(usuario, otroUser);
        if(conversacion==null){
            conversacion = mDataConversacion.create(usuario, otroUser);
        }

        Mensaje mensaje = mDataMensaje.sendMessage(conversacion,dataMensaje,usuario);

        return mensaje.getId();
    }
}
