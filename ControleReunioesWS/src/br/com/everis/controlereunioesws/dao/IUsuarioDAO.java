package br.com.everis.controlereunioesws.dao;

import java.util.List;

import br.com.everis.controlereunioesws.model.Usuario;

public interface IUsuarioDAO {
	
	List<Usuario> gravarUsuarios(List<Usuario> lstUsuario);
	
	void gravarUsuario(Usuario usuario);
	
	Usuario buscarUsuario(Usuario usuario);
	
	Object[] buscarDadosUsuario(Usuario usuario);
	
}
