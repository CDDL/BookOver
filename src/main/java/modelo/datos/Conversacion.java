package modelo.datos;

import javax.persistence.*;
import java.util.List;

/**
 * Created by David on 10/04/2016.
 */

@Entity
@Table(name = "conversaciones")
public class Conversacion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @OneToOne
    private Usuario usuario1;

    @OneToOne
    private Usuario usuario2;


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
