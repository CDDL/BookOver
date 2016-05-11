package modelo.datos.entidades;

import javax.persistence.*;

/**
 * Created by Demils on 09/05/2016.
 */


@Entity
@Table(name = "tokens")
public class Token {

    @Id
    private String mToken;

    @ManyToOne
    @JoinColumn
    private Usuario mUsuario;

    public void setToken(String token) {
        mToken = token;
    }

    public void setUsuario(Usuario usuario) {
        mUsuario = usuario;
    }

    public String getToken() {
        return mToken;
    }
}
