package br.com.everis.controlereunioesws.dao;

import java.util.List;

import br.com.everis.controlereunioesws.model.ReuniaoUsuario;

public interface IReunioesUsuariosDAO {
	void gravar(List<ReuniaoUsuario> reunioesUsuarios);

}
