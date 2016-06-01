package servicios.comunicacionControlador;

import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;

/**
 * Created by Demils on 11/05/2016.
 */
public interface IControllerUsuario {
    boolean existeUsuario(String username);

    boolean isLoginCorrecto(DataLogin login);

    void editarDatosUsuario(int id, Usuario editedData);

    void registrarUsuario(Usuario usuario);

    void asignarLibro(int id, Libro libro);
}
