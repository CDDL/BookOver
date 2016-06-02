package modelo.datos.entidades;

/**
 * Created by David on 01/04/2016.
 */


import modelo.datos.transferencia.DataLogin;
import modelo.datos.transferencia.DataRegister;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Usuario.getByUsername", query = "SELECT p FROM Usuario p WHERE p.username = :username")
})
public class Usuario implements DataLogin, DataRegister {

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
    private List<Libro> listaLibros;

    @XmlTransient
    @OneToMany(mappedBy = "usuarioValorado", targetEntity = Valoracion.class)
    private List<Valoracion> listaValoraciones;

    @XmlTransient
    @OneToMany(mappedBy = "usuarioIniciaTransaccion", targetEntity = Transaccion.class)
    private List<Transaccion> listaTransaccionesIniciadas;

    @XmlTransient
    @OneToMany(mappedBy = "usuarioRecibeTransaccion", targetEntity = Transaccion.class)
    private List<Transaccion> listaTransaccionesRecibidas;



    public void setListaLibros(List listaLibros) {
        this.listaLibros = listaLibros;
    }

    @XmlTransient
    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    @XmlTransient
    public List getListaValoraciones() {
        return listaValoraciones;
    }


    public void setListaValoraciones(List listaValoraciones) {
        this.listaValoraciones = listaValoraciones;
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

    @XmlTransient
    public List<Transaccion> getListaTransaccionesIniciadas() {
        return listaTransaccionesIniciadas;
    }

    public void setListaTransaccionesIniciadas(List<Transaccion> listaTransaccionesIniciadas) {
        this.listaTransaccionesIniciadas = listaTransaccionesIniciadas;
    }

    @XmlTransient
    public List<Transaccion> getListaTransaccionesRecibidas() {
        return listaTransaccionesRecibidas;
    }

    public void setListaTransaccionesRecibidas(List<Transaccion> listaTransaccionesRecibidas) {
        this.listaTransaccionesRecibidas = listaTransaccionesRecibidas;
    }
}
