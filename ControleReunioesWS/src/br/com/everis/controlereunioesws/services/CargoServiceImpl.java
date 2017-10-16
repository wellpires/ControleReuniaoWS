package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.dao.ICargoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.controlereunioesws.model.Cargo;

@Service
public class CargoServiceImpl implements ICargoService {

	@Autowired
	private ICargoDAO cargoDAO = null;
	
	@Override
	public List<Cargo> listarCargos() throws Exception {
		return cargoDAO.listarCargos();
	}

}
