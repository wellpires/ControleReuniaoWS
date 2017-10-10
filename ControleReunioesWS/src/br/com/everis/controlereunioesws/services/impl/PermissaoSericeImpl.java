package br.com.everis.controlereunioesws.services.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.model.Permissao;
import br.com.everis.controlereunioesws.services.IPermissaoService;

@Repository
@Transactional
public class PermissaoSericeImpl extends JpaDao<Integer, Permissao> implements IPermissaoService {

	@Override
	public void gravar(Permissao permissao) {
		entityManager.persist(permissao);
	}

}
