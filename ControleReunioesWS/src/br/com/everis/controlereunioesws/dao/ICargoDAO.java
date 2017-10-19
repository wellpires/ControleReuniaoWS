package br.com.everis.controlereunioesws.dao;

import java.util.List;

import br.com.everis.controlereunioesws.model.Cargo;

public interface ICargoDAO {
	
	List<Cargo> listarCargos() throws Exception;
	
	public Cargo buscarCargo(Long idCargo) throws Exception ;

}
