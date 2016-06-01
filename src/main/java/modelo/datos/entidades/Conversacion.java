package modelo.datos.entidades;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by David on 10/04/2016.
 */


@XmlRootElement
@XmlType(propOrder = {"id", "usuario1", "usuario2"})
@Entity
public class Conversacion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @XmlTransient
    @OneToOne
    @JoinColumn(name="id_usuario1")
    private Usuario usuario1;

    @XmlTransient
    @OneToOne
    @JoinColumn(name="id_usuario2")
    private Usuario usuario2;

    @XmlTransient
    @OneToMany(mappedBy = "conversacion", targetEntity = Mensaje.class)
    private List mensajes;

    public Conversacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public List getMensajes() {
        return mensajes;
    }

    public void setMensajes(List mensajes) {
        this.mensajes = mensajes;
    }
}
