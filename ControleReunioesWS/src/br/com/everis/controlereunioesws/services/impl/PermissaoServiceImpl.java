package br.com.everis.controlereunioesws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IPermissaoDAO;
import br.com.everis.controlereunioesws.model.Permissao;
import br.com.everis.controlereunioesws.services.IPermissaoService;


@Service
public class PermissaoServiceImpl extends JpaDao<Integer, Permissao> implements IPermissaoService {

	@Autowired
	private IPermissaoDAO permissaoDAO = null;
	
	@Override
	public List<Permissao> listarPermissoes() throws Exception {
		return permissaoDAO.listarPermissoes();
	}

	@Override
	public Permissao buscarPermissao(Long idPermissao) {
		return permissaoDAO.buscarPermissao(idPermissao);
	}

}
