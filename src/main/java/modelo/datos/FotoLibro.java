package modelo.datos;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by David on 01/04/2016.
 */
@XmlRootElement
@XmlType(propOrder = {"id", "foto"})
@Entity
@Table(name = "fotoslibros")
public class FotoLibro {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String foto;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name="id_libro", referencedColumnName = "id")
    private Libro libro;

    public FotoLibro(String foto) {
        this.foto = foto;
    }

    public FotoLibro() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
