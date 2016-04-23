package servlets;

import org.glassfish.jersey.server.ResourceConfig;
import servlets.authentication.AuthenticationFilter;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class MiAplicacion extends ResourceConfig {

    public class MyApplication extends Application {
        public MyApplication(){
            packages("servlets.authentication");
            //register(LoggingFilter.class);
            //register(GsonMessageBodyHandler.class);

            //Register Auth Filter here
            register(Hello.class);
            register(AuthenticationFilter.class);
        }
    }
}