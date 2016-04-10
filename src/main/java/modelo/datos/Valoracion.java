package modelo.datos;

import javax.persistence.*;

/**
 * Created by David on 10/04/2016.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "valoraciones")
public class Valoracion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private String descripcion;

    @Column
    private int puntuacion;

    @ManyToOne
    @JoinColumn(name="id_usuario_realiza", referencedColumnName = "id")
    private Usuario usuarioRealiza;

    @ManyToOne
    @JoinColumn(name="id_usuario_valorado", referencedColumnName = "id")
    private Usuario usuarioValorado;

    @ManyToOne
    @JoinColumn(name="id_transaccion", referencedColumnName = "id")
    private Transaccion transaccion;

    public Valoracion(String descripcion, int puntuacion) {
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
    }

    public Valoracion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Usuario getUsuarioRealiza() {
        return usuarioRealiza;
    }

    public void setUsuarioRealiza(Usuario usuarioRealiza) {
        this.usuarioRealiza = usuarioRealiza;
    }

    public Usuario getUsuarioValorado() {
        return usuarioValorado;
    }

    public void setUsuarioValorado(Usuario usuarioValorado) {
        this.usuarioValorado = usuarioValorado;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }
}
