package modelo.datos.transferencia;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Demils on 05/06/2016.
 */

@XmlRootElement
public class DataTransacciones {
    private int id;
    private int tipoTransaccion;
    private String conQuien;

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoTransaccion(int tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public void setConQuien(String conQuien) {
        this.conQuien = conQuien;
    }

    public String getConQuien() {
        return conQuien;
    }

    public int getTipoTransaccion() {
        return tipoTransaccion;
    }

    public int getId() {
        return id;
    }
}
