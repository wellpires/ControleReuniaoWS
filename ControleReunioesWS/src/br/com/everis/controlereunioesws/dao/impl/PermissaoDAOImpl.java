package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IPermissaoDAO;
import br.com.everis.controlereunioesws.model.Permissao;

@Repository
@Transactional
public class PermissaoDAOImpl extends JpaDao<Integer, Permissao> implements IPermissaoDAO {

	@Override
	public List<Permissao> listarPermissoes() throws Exception {
		TypedQuery<Permissao> permissaoQuery = entityManager.createQuery("FROM Permissao", Permissao.class);
		return permissaoQuery.getResultList();
	}

	@Override
	public Permissao buscarPermissao(Long idPermissao) {
		return entityManager.find(Permissao.class, idPermissao);
	}

}
