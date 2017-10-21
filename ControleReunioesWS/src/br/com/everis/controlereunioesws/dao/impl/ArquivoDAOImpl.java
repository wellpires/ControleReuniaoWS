package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IArquivoDAO;
import br.com.everis.controlereunioesws.model.Arquivo;

@Repository
@Transactional
public class ArquivoDAOImpl extends JpaDao<Long, Arquivo>implements IArquivoDAO {

	@Override
	public void gravarArquivos(List<Arquivo> lstArquivos) {
		for (int i = 0; i < lstArquivos.size(); i++) {
			Arquivo arquivo = lstArquivos.get(i);
			entityManager.merge(arquivo);
			if((i % 20) == 0){
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

	@Override
	public void removerReuniao(Arquivo arquivo) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<Arquivo> reuniaoCriteria = builder.createCriteriaDelete(Arquivo.class);
		Root<Arquivo> arquivoRoot = reuniaoCriteria.from(Arquivo.class);
		reuniaoCriteria.where(builder.equal(arquivoRoot.get("reuniao"), arquivo.getReunioes().getIdReuniao()));
		entityManager.createQuery(reuniaoCriteria).executeUpdate();
	}
}
