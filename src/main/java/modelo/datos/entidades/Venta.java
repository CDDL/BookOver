package modelo.datos.entidades;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by David on 05/04/2016.
 */

@XmlRootElement
@XmlType(propOrder = {"id", "fecha", "aceptada", "confirmacion"})
@Entity
@Inheritance(strategy =InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="id")
public class Venta extends Transaccion{

    @Column
    private Boolean confirmacion;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name="id_libro", referencedColumnName = "id")
    private Libro libro;

    public Venta(Boolean confirmacion, Libro libro) {
        this.confirmacion = confirmacion;
        this.libro = libro;
    }

    public Venta() {
    }

    public Boolean getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(Boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
