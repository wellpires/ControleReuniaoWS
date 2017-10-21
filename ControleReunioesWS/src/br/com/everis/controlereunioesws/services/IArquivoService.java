package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.Arquivo;

public interface IArquivoService {
	
	void gravarArquivos(List<Arquivo> lstArquivos) throws Exception;
	
	void removerReuniao(Arquivo arquivo) throws Exception;

}
