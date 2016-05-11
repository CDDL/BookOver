package controlador.filterSecurity;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Demils on 09/05/2016.
 */

@Inherited
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@InterceptorBinding
public @interface IdentificationRequiered { }
