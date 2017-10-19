package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.ReuniaoUsuario;

public interface IReuniaoUsuarioService {
	void gravar(List<ReuniaoUsuario> reunioesUsuarios) throws Exception;

}
