package modelo.datos.transferencia;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by David on 03/06/2016.
 */

@XmlRootElement
public class DataConversacion {

    private int id;
    private String quien;
    private String mensaje;
    private Date cuando;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuien() {
        return quien;
    }

    public void setQuien(String quien) {
        this.quien = quien;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getCuando() {
        return cuando;
    }

    public void setCuando(Date cuando) {
        this.cuando = cuando;
    }
}
