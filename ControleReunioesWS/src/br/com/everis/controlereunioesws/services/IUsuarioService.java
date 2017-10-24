package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.Usuario;

public interface IUsuarioService {
	
	List<Usuario> gravarUsuarios(List<Usuario> usuarios) throws Exception;
	void gravarUsuario(Usuario usuario) throws Exception;
	Object[] buscarDadosUsuario(Usuario usuario) throws Exception;
	List<Usuario> buscarUsuarios(Usuario usuario) throws Exception;
	

}
