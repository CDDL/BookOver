package controladores;

import controladores.comunicacionDatos.IDataUsuario;
import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataListUser;
import modelo.datos.transferencia.DataLogin;
import modelo.datos.transferencia.DataProfileUser;
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
    public void editarDatosUsuario(int id, Usuario usuario) {
        Usuario usuarioIdentificado = mDataUsuario.getById(id);
        usuarioIdentificado.setEmail(usuario.getEmail());
        usuarioIdentificado.setPassword(usuario.getPassword());
        usuarioIdentificado.setUbicacion(usuario.getUbicacion());
        mDataUsuario.actualizar(usuarioIdentificado);
    }

    @Override
    public void asignarLibro(int id, Libro libro) {
        Usuario usuarioIdentificado = mDataUsuario.getById(id);
        usuarioIdentificado.getListaLibros().add(libro);
    }

    @Override
    public DataListUser[] listaOtrasPersonas(Usuario usuario) {
        return mDataUsuario.listaPersonas(usuario);
    }

    @Override
    public Usuario getUserById(int para) {
        return mDataUsuario.getById(para);
    }

    @Override
    public DataProfileUser visualizaUser(Usuario usuario) {
        DataProfileUser resultado = new DataProfileUser();
        resultado.setEmail(usuario.getEmail());
        resultado.setUbicacion(usuario.getUbicacion());
        resultado.setUsername(usuario.getUsername());
        return resultado;
    }

    @Override
    public DataProfileUser getUserLibro(Libro libro) {
        DataProfileUser resultado = new DataProfileUser();
        Usuario usuario = libro.getUsuario();
        resultado.setEmail(usuario.getEmail());
        resultado.setUbicacion(usuario.getUbicacion());
        resultado.setUsername(usuario.getUsername());
        return resultado;

    }

    @Override
    public int getIduserLibro(Libro libro) {
        Usuario usuario = libro.getUsuario();
        return usuario.getId();
    }
}
