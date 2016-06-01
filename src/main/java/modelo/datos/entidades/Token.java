package modelo.datos.entidades;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by Demils on 09/05/2016.
 */


@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Token.getByToken", query = "SELECT p FROM Token p WHERE p.mToken = :token")
})
@XmlRootElement
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mId;

    @Column
    private String mToken;

    @ManyToOne
    @JoinColumn
    private Usuario mUsuario;

    public void setToken(String token) {
        this.mToken = token;
    }

    public void setUsuario(Usuario usuario) {
        this.mUsuario = usuario;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public long getId() {
        return mId;
    }

    public String getToken() {
        return mToken;
    }

    @XmlTransient
    public Usuario getUsuario() {
        return mUsuario;
    }
}
