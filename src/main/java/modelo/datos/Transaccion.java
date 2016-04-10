package modelo.datos;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by David on 03/04/2016.
 */


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "transacciones")
public class Transaccion { //EN CONSTRUCCION

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column
    private Date fecha;
    @Column
    private Boolean aceptada;


}
