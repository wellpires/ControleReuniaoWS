package br.com.everis.controlereunioesws.services.impl;

import java.util.List;

import br.com.everis.controlereunioesws.dao.ICargoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.controlereunioesws.model.Cargo;
import br.com.everis.controlereunioesws.services.ICargoService;

@Service
public class CargoServiceImpl implements ICargoService {

	@Autowired
	private ICargoDAO cargoDAO = null;
	
	@Override
	public List<Cargo> listarCargos() throws Exception {
		return cargoDAO.listarCargos();
	}

	@Override
	public Cargo buscarCargo(Long idCargo) throws Exception {
		return cargoDAO.buscarCargo(idCargo);
	}

}
