package br.com.everis.controlereunioesws.dao;

import java.util.List;

import br.com.everis.controlereunioesws.model.Arquivo;

public interface IArquivoDAO {

	void gravar(List<Arquivo> lstArquivos);
	
}
