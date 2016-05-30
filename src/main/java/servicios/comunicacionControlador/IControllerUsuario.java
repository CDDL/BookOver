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

    void registrarUsuario(Usuario usuario);

    void editarDatosUsuario(Usuario editedData);

    void asignarLibro(Libro libro);

}
