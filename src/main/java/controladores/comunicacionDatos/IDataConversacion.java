package controladores.comunicacionDatos;

import modelo.datos.entidades.Conversacion;
import modelo.datos.entidades.Usuario;

/**
 * Created by David on 02/06/2016.
 */
public interface IDataConversacion {
    Conversacion getByUsers(Usuario usuario, Usuario otroUser);

    Conversacion create(Usuario usuario, Usuario otroUser);
}
