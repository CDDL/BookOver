package modelo.datos.transferencia;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by David on 02/06/2016.
 */

@XmlRootElement
public class DataListConversaciones {

    private int id;

    private String conQuien;


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
}
