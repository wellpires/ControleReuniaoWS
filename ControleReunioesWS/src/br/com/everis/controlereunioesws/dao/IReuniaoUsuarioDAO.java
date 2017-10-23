package br.com.everis.controlereunioesws.dao;

import java.util.List;

import br.com.everis.controlereunioesws.model.ReuniaoUsuario;

public interface IReuniaoUsuarioDAO {
	void gravarReuniaoUsuarios(List<ReuniaoUsuario> reunioesUsuarios) throws Exception;
	void confirmarReuniao(ReuniaoUsuario reuniaoUsuario) throws Exception;

}
