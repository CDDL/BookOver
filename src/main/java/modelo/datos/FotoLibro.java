package modelo.datos;

import javax.persistence.*;

/**
 * Created by David on 01/04/2016.
 */
@Entity
@Table(name = "fotoslibros")
public class FotoLibro {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String foto;

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
