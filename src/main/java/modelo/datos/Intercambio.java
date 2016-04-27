package modelo.datos;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by David on 02/04/2016.
 */

@XmlRootElement
@XmlType(propOrder = {"id","fecha", "aceptada", "confirmacionUser1", "confirmacionUser2"})
@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name = "intercambios")
public class Intercambio extends Transaccion {

    @Column
    private boolean confirmacionUser1;

    @Column
    private boolean confirmacionUser2;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name="id_libro1", referencedColumnName = "id")
    private Libro libroOfrecido;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name="id_libro2", referencedColumnName = "id")
    private Libro libroBuscado;

    public Intercambio(boolean confirmacionUser1, boolean confirmacionUser2, Libro libroOfrecido, Libro libroBuscado) {
        this.confirmacionUser1 = confirmacionUser1;
        this.confirmacionUser2 = confirmacionUser2;
        this.libroOfrecido = libroOfrecido;
        this.libroBuscado = libroBuscado;
    }

    public Intercambio() {
    }

    public boolean isConfirmacionUser1() {
        return confirmacionUser1;
    }

    public void setConfirmacionUser1(boolean confirmacionUser1) {
        this.confirmacionUser1 = confirmacionUser1;
    }

    public boolean isConfirmacionUser2() {
        return confirmacionUser2;
    }

    public void setConfirmacionUser2(boolean confirmacionUser2) {
        this.confirmacionUser2 = confirmacionUser2;
    }

    public Libro getLibroOfrecido() {
        return libroOfrecido;
    }

    public void setLibroOfrecido(Libro libroOfrecido) {
        this.libroOfrecido = libroOfrecido;
    }

    public Libro getLibroBuscado() {
        return libroBuscado;
    }

    public void setLibroBuscado(Libro libroBuscado) {
        this.libroBuscado = libroBuscado;
    }
}
