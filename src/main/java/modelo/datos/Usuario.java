package modelo.datos;

/**
 * Created by David on 01/04/2016.
 */

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String ubicacion;
    @Column
    private String username;

    @OneToMany(mappedBy = "usuario",targetEntity = Libro.class)
    private List listaLibros;

    @OneToMany(mappedBy = "usuarioValorado",targetEntity = Valoracion.class)
    private List listaValoraciones;

    public List getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List listaLibros) {
        this.listaLibros = listaLibros;
    }

    public Usuario(String nombre, String apellidos, String password, String email, String ubicacion, String username) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.email = email;
        this.ubicacion = ubicacion;

        this.username = username;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
