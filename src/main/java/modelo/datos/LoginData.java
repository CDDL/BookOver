package modelo.datos;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Demils on 27/04/2016.
 */

@XmlRootElement
public interface LoginData {
    public String getUsername();
    public String getPassword();
}
