package controladores.comunicacionDatos;

import modelo.datos.entidades.Conversacion;
import modelo.datos.entidades.Mensaje;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataMensaje;

/**
 * Created by David on 02/06/2016.
 */
public interface IDataMensaje {
    Mensaje sendMessage(Conversacion conversacion, DataMensaje dataMensaje, Usuario usuario);
}
