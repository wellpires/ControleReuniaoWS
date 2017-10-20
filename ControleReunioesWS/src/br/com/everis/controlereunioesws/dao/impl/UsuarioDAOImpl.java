package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IUsuarioDAO;
import br.com.everis.controlereunioesws.model.Usuario;

@Repository
@Transactional
public class UsuarioDAOImpl extends JpaDao<Long, Usuario> implements IUsuarioDAO {

	@Override
	public List<Usuario> gravarUsuarios(List<Usuario> lstUsuario) {

		for (int i = 0; i < lstUsuario.size(); i++) {
			Usuario usuario = lstUsuario.get(i);
			entityManager.persist(usuario);
			if ((i % 20) == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
		return lstUsuario;
	}
}
