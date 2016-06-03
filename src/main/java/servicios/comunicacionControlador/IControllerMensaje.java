package servicios.comunicacionControlador;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListConversaciones;
import modelo.datos.transferencia.DataMensaje;

/**
 * Created by David on 02/06/2016.
 */
public interface IControllerMensaje {
    int enviarMensaje(DataMensaje dataMensaje, Usuario usuario, Usuario otroUser);

    DataListConversaciones[] findListConversaciones(Usuario usuario);
}
