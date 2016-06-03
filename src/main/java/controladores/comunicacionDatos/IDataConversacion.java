package controladores.comunicacionDatos;

import modelo.datos.entidades.Conversacion;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListConversaciones;

/**
 * Created by David on 02/06/2016.
 */
public interface IDataConversacion {
    Conversacion getByUsers(Usuario usuario, Usuario otroUser);

    Conversacion create(Usuario usuario, Usuario otroUser);

    DataListConversaciones[] getAllByUser(Usuario usuario);
}
