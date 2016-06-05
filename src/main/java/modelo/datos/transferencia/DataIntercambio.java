package modelo.datos.transferencia;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Demils on 05/06/2016.
 */

@XmlRootElement
public class DataIntercambio {
    private int libroSolicitado;
    private int libroSolicitante;

    public void setLibroSolicitante(int idLibro) {
        this.libroSolicitante = idLibro;
    }

    public void setLibroSolicitado(int idLibro) {
        libroSolicitado = idLibro;
    }

    public int getLibroSolicitante() {
        return libroSolicitante;
    }

    public int getLibroSolicitado() {
        return libroSolicitado;
    }
}
