package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IUsuarioDAO;
import br.com.everis.controlereunioesws.model.Usuario;

@Repository
@Transactional
public class UsuarioDAOImpl extends JpaDao<Long, Usuario> implements IUsuarioDAO {

	@Override
	public List<Usuario> gravarUsuarios(List<Usuario> lstUsuario) throws Exception {

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
	
	@Override
	public Object[] buscarDadosUsuario(Usuario usuario) throws Exception{
		try{
			TypedQuery<Object[]> query = entityManager.createQuery("SELECT u,r FROM Usuario u, Reuniao r INNER JOIN FETCH u.usuarioReuniao ru WHERE u.idUsuario = :idUsuario", Object[].class);
			query.setParameter("idUsuario", usuario.getIdUsuario());
			return query.getSingleResult();
		}catch (NoResultException nre){
			return null;
		}
	}

	@Override
	public void gravarUsuario(Usuario usuario) throws Exception{
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Usuario> criteria = builder.createCriteriaUpdate(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.set(root.get("usuario"), usuario.getUsuario());
		criteria.set(root.get("senha"), usuario.getSenha());
		criteria.where(builder.equal(root.get("idUsuario"), usuario.getIdUsuario()));
		entityManager.createQuery(criteria).executeUpdate();
		
	}

	@Override
	public List<Usuario> buscarUsuarios(Usuario usuario) throws Exception {
		return null;
	}

}
