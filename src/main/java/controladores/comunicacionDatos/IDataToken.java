package controladores.comunicacionDatos;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;

/**
 * Created by Demils on 11/05/2016.
 */
public interface IDataToken {
    Token guardarToken(Token token);

    Token getByToken(String token);

    void test();

    Usuario getUserFromToken(Token token);
}
