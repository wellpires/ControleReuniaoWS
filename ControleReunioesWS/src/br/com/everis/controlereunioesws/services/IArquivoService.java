package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.Arquivo;

public interface IArquivoService {
	
	void gravar(List<Arquivo> lstArquivos) throws Exception;

}
