package servicios.interceptorSecurity;

import servicios.comunicacionControlador.IControllerToken;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by Demils on 09/05/2016.
 */

@Interceptor
@AuthenticationRequired
@Stateful
public class InterceptorAuthentication {

    @Context
    HttpHeaders headers;

    @Inject
    IControllerToken controladorToken;

    @AroundInvoke
    public Object comprobarLogin(InvocationContext context) throws Exception {
        String token = headers.getHeaderString("Authentication");
        if (!controladorToken.existeToken(token)) return Response.status(Response.Status.UNAUTHORIZED).build();
        return context.proceed();
    }
}
