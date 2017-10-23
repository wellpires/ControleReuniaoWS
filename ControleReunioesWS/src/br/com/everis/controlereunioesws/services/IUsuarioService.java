package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.Usuario;

public interface IUsuarioService {
	
	List<Usuario> gravarUsuarios(List<Usuario> usuarios);
	void gravarUsuario(Usuario usuario);
	Object[] buscarDadosUsuario(Usuario usuario);

}
