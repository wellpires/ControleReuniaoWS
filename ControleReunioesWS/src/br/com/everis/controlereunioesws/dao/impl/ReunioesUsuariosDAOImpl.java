package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IReunioesUsuariosDAO;
import br.com.everis.controlereunioesws.model.ReuniaoUsuario;

@Repository
@Transactional
public class ReunioesUsuariosDAOImpl extends JpaDao<Long, ReuniaoUsuario> implements IReunioesUsuariosDAO {

	@Override
	public void gravarReuniaoUsuarios(List<ReuniaoUsuario> reunioesUsuarios) {
		for (int i = 0; i < reunioesUsuarios.size(); i++) {
			ReuniaoUsuario arquivo = reunioesUsuarios.get(i);
			entityManager.persist(arquivo);
			if((i % 20) == 0){
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

}
