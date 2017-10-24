package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.ReuniaoUsuario;
import br.com.everis.controlereunioesws.model.Usuario;

public interface IReuniaoUsuarioService {
	void gravarReuniaoUsuario(List<ReuniaoUsuario> reunioesUsuarios) throws Exception;
	void confirmarReuniao(ReuniaoUsuario reuniaoUsuario) throws Exception;
	List<Usuario> buscarUsuarios(ReuniaoUsuario reuniaoUsuario) throws Exception;

}
