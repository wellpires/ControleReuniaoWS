package br.com.everis.controlereunioesws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.controlereunioesws.dao.IUsuarioDAO;
import br.com.everis.controlereunioesws.model.Usuario;
import br.com.everis.controlereunioesws.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDAO = null;
	
	@Override
	public List<Usuario> gravarUsuarios(List<Usuario> usuarios) throws Exception {
		return usuarioDAO.gravarUsuarios(usuarios);
	}

	@Override
	public Object[] buscarDadosUsuario(Usuario usuario) throws Exception {
		return usuarioDAO.buscarDadosUsuario(usuario);
	}

	@Override
	public void gravarUsuario(Usuario usuario) throws Exception {
		usuarioDAO.gravarUsuario(usuario);
	}

	@Override
	public List<Usuario> buscarUsuarios(Usuario usuario) throws Exception {
		return usuarioDAO.buscarUsuarios(usuario);
	}

}
