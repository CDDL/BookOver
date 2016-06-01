package modelo.datos.entidades;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by David on 10/04/2016.
 */
@XmlRootElement
@XmlType(propOrder = {"id", "fecha", "mensaje"})
@Entity
public class Mensaje {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name="id_conversacion", referencedColumnName = "id")
    private Conversacion conversacion;

    @XmlTransient
    @OneToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @Column
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column
    private String mensaje;

    public Mensaje(Date fecha, String mensaje) {
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    public Mensaje() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conversacion getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversacion conversacion) {
        this.conversacion = conversacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
