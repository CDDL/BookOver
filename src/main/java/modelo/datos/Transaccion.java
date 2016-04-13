package modelo.datos;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by David on 03/04/2016.
 */


@Entity
@Inheritance(strategy =InheritanceType.JOINED)
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column
    private Date fecha;
    @Column
    private Boolean aceptada;

    public Set getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

    @JoinTable(name = "mantienen",
            joinColumns = {@JoinColumn(name = "id_transaccion", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_usuario", referencedColumnName = "id")})
    @ManyToMany(targetEntity=Usuario.class)
    private Set usuarios;


    public Transaccion() {

    }

    public Transaccion(Date fecha, Boolean aceptada) {

        this.fecha = fecha;
        this.aceptada = aceptada;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getAceptada() {
        return aceptada;
    }

    public void setAceptada(Boolean aceptada) {
        this.aceptada = aceptada;
    }

}
