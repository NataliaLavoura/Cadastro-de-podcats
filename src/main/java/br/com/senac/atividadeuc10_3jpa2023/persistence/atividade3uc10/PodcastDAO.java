package br.com.senac.atividadeuc10_3jpa2023.persistence.atividade3uc10;

import com.google.protobuf.Any;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author natib
 */
public class PodcastDAO {

    public void input(Podcast p) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw e;

        } finally {

            JPAUtil.closeEtityManager();

        }

    }

    public List<Podcast> listar(String pesqProdutor) {

        EntityManager em = JPAUtil.getEntityManager();
        List podcast = null;

        try {
            String textoQuery = "SELECT p FROM Podcast p "
                    + "WHERE (:produtor is null OR p.produtor LIKE :produtor)";
            Query consulta = em.createQuery(textoQuery);

            consulta.setParameter("produtor", pesqProdutor.isEmpty() ? null : "%"
                    + pesqProdutor + "%");
            podcast = consulta.getResultList();

        } finally {

            JPAUtil.closeEtityManager();

        }

        return podcast;

    }

    public void deletar(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Podcast p = em.find(Podcast.class, id);

            if (p != null) {

                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();

            }

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw e;

        } finally {

            JPAUtil.closeEtityManager();

        }
    }
}
