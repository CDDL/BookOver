package servlets;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class MiAplicacion extends ResourceConfig {

    public class MyApplication extends Application {
        public Set<Class<?>> getClasses() {
            Set<Class<?>> s = new HashSet<Class<?>>();
            s.add(Hello.class);
            return s;
        }
    }
}