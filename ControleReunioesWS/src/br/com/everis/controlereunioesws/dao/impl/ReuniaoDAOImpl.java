package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IReuniaoDAO;
import br.com.everis.controlereunioesws.model.Reuniao;

@Repository
@Transactional
public class ReuniaoDAOImpl extends JpaDao<Integer, Reuniao> implements IReuniaoDAO {

	@Override
	public Reuniao gravarReuniao(Reuniao reuniao) throws Exception {
		entityManager.persist(reuniao);
		return reuniao;
	}

	@Override
	public void editarReuniao(Reuniao reuniao) throws Exception {
		entityManager.merge(reuniao);
	}

	@Override
	public void removerReuniao(Reuniao reuniao) throws Exception {
		Reuniao r = buscarReuniao(reuniao);
		entityManager.remove(r);
	}

	@Override
	public Reuniao buscarReuniao(Reuniao reuniao) throws Exception {
		return entityManager.find(Reuniao.class, reuniao.getIdReuniao());
	}

	@Override
	public List<Reuniao> buscarReunioes(Reuniao reuniao) throws Exception {
		TypedQuery<Reuniao> reuniaoQuery = entityManager.createQuery(
				"SELECT r FROM Reuniao r WHERE  r.dtInicio > :hoje ORDER BY r.dtInicio ASC", Reuniao.class);
		reuniaoQuery.setParameter("hoje", reuniao.getDtInicio());
		return reuniaoQuery.getResultList();
	}

	@Override
	public List<Reuniao> listarReunioes() throws Exception {
		TypedQuery<Reuniao> reuniaoQuery = entityManager.createQuery("FROM Reuniao r ORDER BY r.dtInicio ASC",
				Reuniao.class);
		return reuniaoQuery.getResultList();
	}

}
