package modelo.datos.transferencia;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by David on 02/06/2016.
 */

@XmlRootElement
public class DataListConversaciones {

    private int id;

    private String conQuien;
    private int idPersona;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConQuien() {
        return conQuien;
    }

    public void setConQuien(String conQuien) {
        this.conQuien = conQuien;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }
}
