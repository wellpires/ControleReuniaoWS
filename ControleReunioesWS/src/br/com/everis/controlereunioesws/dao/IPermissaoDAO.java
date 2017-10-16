package br.com.everis.controlereunioesws.dao;

import java.util.List;

import br.com.everis.controlereunioesws.model.Permissao;

public interface IPermissaoDAO {
	List<Permissao> listarPermissoes() throws Exception;;

}
