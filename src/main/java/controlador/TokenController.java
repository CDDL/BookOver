package controlador;

import modelo.datos.entidades.Token;
import modelo.datos.entidades.Usuario;
import modelo.servicios.TokenService;

import javax.inject.Inject;
import java.security.SecureRandom;

/**
 * Created by Demils on 09/05/2016.
 */
public class TokenController {

    @Inject
    TokenService tokenService;

    public boolean existsToken(String token) {
        Token mToken = tokenService.getByToken(token);
        return mToken != null;
    }

    String generarToken(Usuario usuario) {
        Token token = new Token();
        token.setToken(generarTokenRandom());
        token.setUsuario(usuario);

        tokenService.add(token);

        return token.getToken();
    }

    private String generarTokenRandom() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }
}
