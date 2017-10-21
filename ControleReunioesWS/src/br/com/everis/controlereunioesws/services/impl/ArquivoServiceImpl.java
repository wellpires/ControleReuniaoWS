package br.com.everis.controlereunioesws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.controlereunioesws.dao.IArquivoDAO;
import br.com.everis.controlereunioesws.model.Arquivo;
import br.com.everis.controlereunioesws.services.IArquivoService;

@Service
public class ArquivoServiceImpl implements IArquivoService {

	@Autowired
	private IArquivoDAO arquivoDAO = null;
	
	@Override
	public void gravarArquivos(List<Arquivo> lstArquivos) throws Exception {
		arquivoDAO.gravarArquivos(lstArquivos);
	}

	@Override
	public void removerReuniao(Arquivo arquivo) throws Exception {
		arquivoDAO.removerReuniao(arquivo);
	}

}
