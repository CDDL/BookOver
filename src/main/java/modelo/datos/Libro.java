package modelo.datos;

/**
 * Created by David on 01/04/2016.
 */

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column
    private String titulo;
    @Column
    private String autor;
    @Column
    private String editorial;
    @Column
    private String isbn13;
    @Column
    private String isbn10;
    @Column
    private String estado;
    @Column
    private String infoAdicional;
    @Column
    private Boolean esPrestable;
    @Column
    private Boolean esVendible;
    @Column
    private Boolean esIntercambiable;
    @Column
    private int precio;

    @OneToMany(mappedBy = "libro", targetEntity = FotoLibro.class)
    private List fotos;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public List getFotos() {
        return fotos;
    }

    public void setFotos(List fotos) {
        this.fotos = fotos;
    }

    public Libro(String titulo, String autor, String editorial, String isbn13, String isbn10, String estado, String infoAdicional, Boolean esPrestable, Boolean esVendible, Boolean esIntercambiable, int precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;

        this.estado = estado;
        this.infoAdicional = infoAdicional;
        this.esPrestable = esPrestable;
        this.esVendible = esVendible;
        this.esIntercambiable = esIntercambiable;
        this.precio = precio;
    }

    public Libro() {
        this.esPrestable = false;
        this.esVendible = false;
        this.esIntercambiable = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    public Boolean getEsPrestable() {
        return esPrestable;
    }

    public void setEsPrestable(Boolean esPrestable) {
        this.esPrestable = esPrestable;
    }

    public Boolean getEsVendible() {
        return esVendible;
    }

    public void setEsVendible(Boolean esVendible) {
        this.esVendible = esVendible;
    }

    public Boolean getEsIntercambiable() {
        return esIntercambiable;
    }

    public void setEsIntercambiable(Boolean esIntercambiable) {
        this.esIntercambiable = esIntercambiable;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
