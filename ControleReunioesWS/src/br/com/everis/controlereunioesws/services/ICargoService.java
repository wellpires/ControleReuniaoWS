package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.Cargo;

public interface ICargoService {

	List<Cargo> listarCargos() throws Exception;

	Cargo buscarCargo(Long idCargo) throws Exception;

}
