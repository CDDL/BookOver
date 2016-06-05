package modelo.persistencia;

import controladores.comunicacionDatos.IDataLibro;
import controladores.comunicacionDatos.IDataValoraciones;
import modelo.datos.entidades.Libro;
import modelo.datos.entidades.Usuario;
import modelo.datos.entidades.Valoracion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by David on 01/06/2016.
 */

@Stateless
public class JpaValoraciones implements IDataValoraciones {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    @Override
    public void addValoracion(Valoracion valoracion) {
        mEntityManager.persist(valoracion);
    }
}
