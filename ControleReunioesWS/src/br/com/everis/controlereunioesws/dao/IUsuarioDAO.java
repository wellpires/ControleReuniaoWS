package br.com.everis.controlereunioesws.dao;

import java.util.List;

import br.com.everis.controlereunioesws.model.Usuario;

public interface IUsuarioDAO {
	
	List<Usuario> gravarUsuarios(List<Usuario> lstUsuario) throws Exception;
	
	void gravarUsuario(Usuario usuario) throws Exception;
	
	List<Usuario> buscarUsuarios(Usuario usuario) throws Exception;
	
	Object[] buscarDadosUsuario(Usuario usuario) throws Exception;
	
}
