package modelo.datos.entidades;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.Set;

/**
 * Created by David on 03/04/2016.
 */

@XmlRootElement
@XmlType(propOrder = {"id", "fecha", "aceptada"})
@Entity
@Inheritance(strategy =InheritanceType.JOINED)
public class Transaccion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column
    private Boolean aceptada;

    @XmlTransient
    @ManyToOne(targetEntity=Usuario.class)
    @JoinColumn(name = "id_usuario_inicia", referencedColumnName = "id")
    private Usuario usuarioIniciaTransaccion;

    @XmlTransient
    @ManyToOne(targetEntity=Usuario.class)
    @JoinColumn(name = "id_usuario_recibe", referencedColumnName = "id")
    private Usuario usuarioRecibeTransaccion;


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

    @XmlTransient
    public Usuario getUsuarioIniciaTransaccion() {
        return usuarioIniciaTransaccion;
    }

    public void setUsuarioIniciaTransaccion(Usuario usuarioIniciaTransaccion) {
        this.usuarioIniciaTransaccion = usuarioIniciaTransaccion;
    }

    @XmlTransient
    public Usuario getUsuarioRecibeTransaccion() {
        return usuarioRecibeTransaccion;
    }

    public void setUsuarioRecibeTransaccion(Usuario usuarioRecibeTransaccion) {
        this.usuarioRecibeTransaccion = usuarioRecibeTransaccion;
    }
}
