package modelo.datos.transferencia;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Demils on 27/04/2016.
 */

@XmlRootElement
public interface DataLogin {
    String getUsername();

    String getPassword();

    void setUsername(String username);

    void setPassword(String password);
}
