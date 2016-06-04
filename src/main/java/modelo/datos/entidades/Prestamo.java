package modelo.datos.entidades;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by David on 03/04/2016.
 */

@XmlRootElement
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Prestamo extends Transaccion{

    @Column
    private boolean confirmacionDevolucion;

    @Column
    private boolean mLibroRecibido;

    @Column
    private boolean mLibroDevuelto;

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

    public boolean getConfirmacionDevolucion() {
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

    public boolean getLibroRecibido() {
        return mLibroRecibido;
    }

    public void setLibroRecibido(boolean libroRecibido) {
        mLibroRecibido = libroRecibido;
    }

    public void setLibroDevuelto(boolean libroDevuelto) {
        mLibroDevuelto = libroDevuelto;
    }

    public boolean getLibroDevuelto() {
        return mLibroDevuelto;
    }
}
