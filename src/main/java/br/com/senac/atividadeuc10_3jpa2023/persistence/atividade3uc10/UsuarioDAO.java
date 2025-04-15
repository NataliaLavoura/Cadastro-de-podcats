package br.com.senac.atividadeuc10_3jpa2023.persistence.atividade3uc10;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    public Usuario checkLogin(String login, String password) {

        Usuario user = null;

        EntityManager em = JPAUtil.getEntityManager();

        try {

            TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM Usuario u WHERE"
                    + " u.login = :nomePar AND u.senha = :senhaPar", Usuario.class);
            consulta.setParameter("nomePar", login);
            consulta.setParameter("senhaPar", password);

            if (login.equals(consulta.getSingleResult().getLogin())
                    && password.equals(consulta.getSingleResult().getSenha())) {
                user = consulta.getSingleResult();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Falha ao cadastrar!\n" + e.getMessage(),
                    "Login", JOptionPane.ERROR_MESSAGE);

        }

        return user;
    }
}
