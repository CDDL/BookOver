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

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAceptada(Boolean aceptada) {
        this.aceptada = aceptada;
    }

    public void setUsuarioIniciaTransaccion(Usuario usuarioIniciaTransaccion) {
        this.usuarioIniciaTransaccion = usuarioIniciaTransaccion;
    }

    public void setUsuarioRecibeTransaccion(Usuario usuarioRecibeTransaccion) {
        this.usuarioRecibeTransaccion = usuarioRecibeTransaccion;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Boolean getAceptada() {
        return aceptada;
    }

    @XmlTransient
    public Usuario getUsuarioRecibeTransaccion() {
        return usuarioRecibeTransaccion;
    }

    @XmlTransient
    public Usuario getUsuarioIniciaTransaccion() {
        return usuarioIniciaTransaccion;
    }
}
