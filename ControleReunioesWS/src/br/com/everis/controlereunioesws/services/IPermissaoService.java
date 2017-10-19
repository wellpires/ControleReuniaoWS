package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.Permissao;

public interface IPermissaoService {
	List<Permissao> listarPermissoes() throws Exception;

	Permissao buscarPermissao(Long idPermissao);
}
