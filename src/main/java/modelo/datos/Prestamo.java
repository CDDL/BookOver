package modelo.datos;

import javax.persistence.*;

/**
 * Created by David on 03/04/2016.
 */

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name = "prestamos")
public class Prestamo extends Transaccion{

    @Column
    private Boolean confirmacionDevolucion;

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
