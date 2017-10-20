package br.com.everis.controlereunioesws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.controlereunioesws.dao.IReunioesUsuariosDAO;
import br.com.everis.controlereunioesws.model.ReuniaoUsuario;
import br.com.everis.controlereunioesws.services.IReuniaoUsuarioService;

@Service
public class ReuniaoUsuarioService implements IReuniaoUsuarioService {

	@Autowired
	private IReunioesUsuariosDAO reunioesUsuarioDAO = null;
	
	@Override
	public void gravarReuniaoUsuarios(List<ReuniaoUsuario> reunioesUsuarios) throws Exception {
		reunioesUsuarioDAO.gravarReuniaoUsuarios(reunioesUsuarios);
	}

}
