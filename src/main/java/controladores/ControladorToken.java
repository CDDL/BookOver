package controladores;

import controladores.comunicacionDatos.IDataToken;
import controladores.comunicacionDatos.IDataUsuario;
import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;
import servicios.comunicacionControlador.IControllerToken;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Demils on 09/05/2016.
 */
@Stateful
public class ControladorToken implements IControllerToken {

    @Inject
    IDataToken mDataToken;

    @Inject
    IDataUsuario mDataUsuario;

    private String generarStringRandom() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Token generarToken(DataLogin login) {
        Usuario usuario = mDataUsuario.getByUsername(login.getUsername());

        Token token = new Token();
        token.setToken(generarStringRandom());
        token.setUsuario(usuario);

        mDataToken.guardarToken(token);
        return token;
    }

    @Override
    public boolean existeToken(String token) {
        return mDataToken.getByToken(token) != null;
    }

    @Override
    public void test() {
        mDataToken.test();
    }

    @Override
    public Usuario getUserFromToken(String tokenString) {
        Token token = mDataToken.getByToken(tokenString);
        return mDataToken.getUserFromToken(token);
    }

}
