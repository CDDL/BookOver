package controlador.filterSecurity;

import controlador.TokenController;

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
@IdentificationRequiered
public class AuthenticationInterceptor {

    @Context
    HttpHeaders headers;

    @Inject
    TokenController tokenController;

    @AroundInvoke
    public Object comprobarLogin(InvocationContext context) throws Exception {
        String token = headers.getHeaderString("Authentication");
        if (!tokenController.existsToken(token)) return Response.status(Response.Status.UNAUTHORIZED).build();
        return context.proceed();
    }
}
