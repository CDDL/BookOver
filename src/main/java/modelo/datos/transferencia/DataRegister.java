package modelo.datos.transferencia;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Demils on 23/05/2016.
 */
@XmlRootElement
public interface DataRegister {
    void setUsername(String username);

    void setPassword(String password);

    void setEmail(String email);

    void setUbicacion(String ubicacion);
}
