package br.com.everis.controlereunioesws.services.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.model.Reuniao;
import br.com.everis.controlereunioesws.services.IReuniaoService;

@Repository
@Transactional
public class ReuniaoServiceImpl extends JpaDao<Integer, Reuniao> implements IReuniaoService {

	@Override
	public void salvar(Reuniao reuniao) throws Exception {
		entityManager.persist(reuniao);
	}

	@Override
	public List<Reuniao> buscarReunioes(Date dtHoje) throws Exception {
		TypedQuery<Reuniao> reuniaoQuery = entityManager.createQuery("SELECT o FROM Reuniao WHERE  o.dtInicio < :hoje", Reuniao.class);
		reuniaoQuery.setParameter("hoje", dtHoje);
		return reuniaoQuery.getResultList();
	}

}
