package controladores.comunicacionDatos;

import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListUser;

/**
 * Created by Demils on 11/05/2016.
 */
public interface IDataUsuario {
    Usuario getByUsername(String username);

    void addUsuario(Usuario usuario);

    Usuario getById(int id);

    DataListUser[] listaPersonas(Usuario usuario);
}
