package br.com.senac.atividadeuc10_3jpa2023.persistence.atividade3uc10;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author natib
 */
public class JPAUtil {

    private static final String PERSISTENCE_UNIT = "Cenaflix-PU";

    private static EntityManager em;
    private static EntityManagerFactory fabrica;

    public static EntityManager getEntityManager() {

        if (fabrica == null || !fabrica.isOpen()) {

            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }

        if (em == null || !em.isOpen()) //cria se em nulo ou se o entity manager foi fechado
        {
            em = fabrica.createEntityManager();
        }
        return em;
    }

    public static void closeEtityManager() {
        if (em.isOpen() && em != null) {
            em.close();
            fabrica.close();
        }
    }
}
