package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 18:01
 */
public class HibernateUtil {

    public static final String PERSISTENCE_UNIT_NAME = "library";
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void close() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
