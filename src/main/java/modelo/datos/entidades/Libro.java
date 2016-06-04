package modelo.datos.entidades;

/**
 * Created by David on 01/04/2016.
 */

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
//@XmlType(propOrder = {"id", "titulo", "autor", "editorial", "isbn", "estado", "infoAdicional","esPrestable","esVendible","esIntercambiable","precio"})
@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Libro.getId", query = "SELECT l FROM Libro l WHERE l.visible = true"),
        @NamedQuery(name = "Libro.getLista", query = "SELECT l FROM Libro l JOIN l.usuario u WHERE l.visible = true AND u = :user")
})
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String titulo;
    @Column
    private String autor;
    @Column
    private String editorial;
    @Column
    private String isbn;
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

    @Column
    private boolean visible;

    @XmlTransient
    @OneToMany(mappedBy = "libro", targetEntity = FotoLibro.class)
    private List fotos;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public List getFotos() {
        return fotos;
    }

    public void setFotos(List fotos) {
        this.fotos = fotos;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
