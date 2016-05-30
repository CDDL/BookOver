package controladores;

import controladores.comunicacionDatos.IDataUsuario;
import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import servicios.comunicacionControlador.IControllerUsuario;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.Objects;

/**
 * Created by David on 07/05/2016.
 */
@Stateful
public class ControladorUsuario implements IControllerUsuario {

    @Inject
    private IDataUsuario mDataUsuario;

    @Inject
    private ControladorDatosUsuarioIdentificado mControladorDatosUsuarioIdentificado;

    @Override
    public boolean existeUsuario(String username) {
        Usuario usuario = mDataUsuario.getByUsername(username);
        return usuario != null;
    }

    @Override
    public boolean isLoginCorrecto(DataLogin login) {
        String contraseñaGuardada = mDataUsuario.getByUsername(login.getUsername()).getPassword();
        String contraseñaAComprobar = login.getPassword();
        return Objects.equals(contraseñaAComprobar, contraseñaGuardada);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        mDataUsuario.addUsuario(usuario);
    }

    @Override
    public void editarDatosUsuario(Usuario usuario) {
        Usuario usuarioIdentificado = mControladorDatosUsuarioIdentificado.getUsuarioIndentificado();
        usuarioIdentificado.setEmail(usuario.getEmail());
        usuarioIdentificado.setPassword(usuario.getPassword());
        usuarioIdentificado.setUbicacion(usuario.getUbicacion());
    }

    @Override
    public void asignarLibro(Libro libro) {
        Usuario usuarioIdentificado = mControladorDatosUsuarioIdentificado.getUsuarioIndentificado();
        usuarioIdentificado.getListaLibros().add(libro);
    }
}
