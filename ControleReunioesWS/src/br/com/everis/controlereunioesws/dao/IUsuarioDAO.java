package br.com.everis.controlereunioesws.dao;

import java.util.List;

import br.com.everis.controlereunioesws.model.Usuario;

public interface IUsuarioDAO {
	
	List<Usuario> gravarUsuarios(List<Usuario> lstUsuario);
	
}
