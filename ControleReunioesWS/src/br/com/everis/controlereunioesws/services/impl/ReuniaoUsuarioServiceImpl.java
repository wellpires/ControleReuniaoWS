package br.com.everis.controlereunioesws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.controlereunioesws.dao.IReuniaoUsuarioDAO;
import br.com.everis.controlereunioesws.model.ReuniaoUsuario;
import br.com.everis.controlereunioesws.services.IReuniaoUsuarioService;

@Service
public class ReuniaoUsuarioServiceImpl implements IReuniaoUsuarioService {

	@Autowired
	private IReuniaoUsuarioDAO reuniaoUsuarioDAO = null;
	
	@Override
	public void gravarReuniaoUsuario(List<ReuniaoUsuario> reunioesUsuarios) throws Exception {
		reuniaoUsuarioDAO.gravarReuniaoUsuarios(reunioesUsuarios);
	}

	@Override
	public void confirmarReuniao(ReuniaoUsuario reuniaoUsuario) throws Exception {
		reuniaoUsuarioDAO.confirmarReuniao(reuniaoUsuario);
	}

}
