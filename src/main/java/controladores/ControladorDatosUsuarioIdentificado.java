package controladores;

import modelo.datos.entidades.Usuario;
import servicios.comunicacionControlador.IControllerToken;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * Created by Demils on 16/05/2016.
 */

@Stateful
public class ControladorDatosUsuarioIdentificado {

    @Context
    HttpHeaders headers;

    @Inject
    private IControllerToken controladorToken;


    public Usuario getUsuarioIndentificado() {
        String token = headers.getHeaderString("Authentication");
        return controladorToken.getUserFromToken(token);
    }
}
