package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IReuniaoUsuarioDAO;
import br.com.everis.controlereunioesws.model.ReuniaoUsuario;
import br.com.everis.controlereunioesws.model.Usuario;

@Repository
@Transactional
public class ReuniaoUsuarioDAOImpl extends JpaDao<Long, ReuniaoUsuario> implements IReuniaoUsuarioDAO {

	@Override
	public void gravarReuniaoUsuarios(List<ReuniaoUsuario> reunioesUsuarios) throws Exception  {
		for (int i = 0; i < reunioesUsuarios.size(); i++) {
			ReuniaoUsuario arquivo = reunioesUsuarios.get(i);
			entityManager.persist(arquivo);
			if((i % 20) == 0){
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

	@Override
	public void confirmarReuniao(ReuniaoUsuario reuniaoUsuario) throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<ReuniaoUsuario> criteriaUpdate = builder.createCriteriaUpdate(ReuniaoUsuario.class);
		Root<ReuniaoUsuario> root = criteriaUpdate.from(ReuniaoUsuario.class);
		criteriaUpdate.set(root.get("confirmado"), reuniaoUsuario.getConfirmado());
		criteriaUpdate.where(builder.and(builder.equal(root.get("pk").get("reuniao"), reuniaoUsuario.getPk().getReuniao().getIdReuniao()), builder.equal(root.get("pk").get("usuario"), reuniaoUsuario.getPk().getUsuario().getIdUsuario())));
		entityManager.createQuery(criteriaUpdate).executeUpdate();
		
	}

	@Override
	public List<Usuario> buscarUsuarios(ReuniaoUsuario reuniaoUsuario) throws Exception {
		TypedQuery<Usuario> usuarioQuery = entityManager.createQuery("SELECT u FROM Usuario u INNER JOIN u.usuarioReuniao ru WHERE ru.pk.reuniao.idReuniao = :idReuniao", Usuario.class);
		usuarioQuery.setParameter("idReuniao", reuniaoUsuario.getReuniao().getIdReuniao());
		return usuarioQuery.getResultList();
	}

}
