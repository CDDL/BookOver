package modelo.datos.entidades;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by David on 03/04/2016.
 */

@XmlRootElement
@XmlType(propOrder = {"id", "fecha", "aceptada", "confirmacionDevolucion"})
@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name = "prestamos")
public class Prestamo extends Transaccion{

    @Column
    private Boolean confirmacionDevolucion;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name="id_libro", referencedColumnName = "id")
    private Libro libro;

    public Prestamo(Boolean confirmacionDevolucion, Libro libro) {
        this.confirmacionDevolucion = confirmacionDevolucion;
        this.libro = libro;
    }

    public Prestamo() {
    }

    public Boolean getConfirmacionDevolucion() {
        return confirmacionDevolucion;
    }

    public void setConfirmacionDevolucion(Boolean confirmacionDevolucion) {
        this.confirmacionDevolucion = confirmacionDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
