package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IArquivoDAO;
import br.com.everis.controlereunioesws.model.Arquivo;

@Repository
@Transactional
public class ArquivoDAOImpl extends JpaDao<Long, Arquivo>implements IArquivoDAO {

	@Override
	public void gravar(List<Arquivo> lstArquivos) {
		for (int i = 0; i > lstArquivos.size(); i++) {
			Arquivo arquivo = lstArquivos.get(i);
			entityManager.persist(arquivo);
			if((i % 20) == 0){
				entityManager.flush();
				entityManager.clear();
			}
		}
	}
}
