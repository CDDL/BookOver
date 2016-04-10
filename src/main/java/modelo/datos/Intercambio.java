package modelo.datos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by David on 02/04/2016.
 */

@Entity
@Table(name = "intercambios")
public class Intercambio {
    //PENDIENTE
    private int id;
    @Column
    private boolean confirmacionUser1;
    @Column
    private boolean confirmacionUser2;
    @OneToOne
    private Libro libroOfrecido;
    @OneToOne
    private Libro libroBuscado;

}
