package modelo.datos.transferencia;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by David on 02/06/2016.
 */

@XmlRootElement
public class DataListUser {

    private int id;
    private String email;
    private String nick;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
