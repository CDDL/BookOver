package servicios.comunicacionControlador;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.datos.transferencia.DataLogin;

/**
 * Created by Demils on 11/05/2016.
 */
public interface IControllerToken {
    Token generarToken(DataLogin login);

    boolean existeToken(String token);

    void test();

    Usuario getUserFromToken(String token);

    void deleteToken(String token);
}
