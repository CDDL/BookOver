package modelo.datos.entidades;

/**
 * Created by David on 01/04/2016.
 */


import modelo.datos.transfer.LoginData;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"id", "password", "email", "ubicacion", "username"})
@Entity
@Table(name = "usuarios")
public class Usuario implements LoginData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String ubicacion;

    @Column
    private String username;

    @XmlTransient
    @OneToMany(mappedBy = "usuario", targetEntity = Libro.class)
    private List listaLibros;

    @XmlTransient
    @OneToMany(mappedBy = "usuarioValorado", targetEntity = Valoracion.class)
    private List listaValoraciones;

    @XmlTransient
    public List getListaLibros() {
        return listaLibros;
    }


    public void setListaLibros(List listaLibros) {
        this.listaLibros = listaLibros;
    }

    public Usuario(String password, String email, String ubicacion, String username) {

        this.password = password;
        this.email = email;
        this.ubicacion = ubicacion;

        this.username = username;
    }

    @XmlTransient
    public List getListaValoraciones() {
        return listaValoraciones;
    }


    public void setListaValoraciones(List listaValoraciones) {
        this.listaValoraciones = listaValoraciones;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
