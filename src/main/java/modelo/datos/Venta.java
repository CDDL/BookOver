package modelo.datos;

import javax.persistence.*;

/**
 * Created by David on 05/04/2016.
 */

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name = "ventas")
public class Venta {

    @Column
    private Boolean confirmacion;

    @ManyToOne
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
