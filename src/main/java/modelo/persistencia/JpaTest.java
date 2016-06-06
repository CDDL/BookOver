package modelo.persistencia;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by Demils on 23/05/2016.
 */

@Stateful
public class JpaTest {

    @PersistenceContext(unitName = "BookOver", type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    public void vaciarDb() {
        mEntityManager.createQuery("DELETE FROM Valoracion m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM Mensaje m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM Conversacion m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM FotoLibro m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM Intercambio m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM Prestamo m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM Token m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM Transaccion m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM Libro m").executeUpdate();
        mEntityManager.createQuery("DELETE FROM Usuario m").executeUpdate();

        mEntityManager.createQuery("DELETE FROM Venta m").executeUpdate();
    }

}
