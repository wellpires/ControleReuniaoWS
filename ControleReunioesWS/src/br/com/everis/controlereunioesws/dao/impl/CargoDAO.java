package br.com.everis.controlereunioesws.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.ICargoDAO;
import br.com.everis.controlereunioesws.model.Cargo;

@Repository
@Transactional
public class CargoDAO extends JpaDao<Long, Cargo> implements ICargoDAO {

	@Override
	public List<Cargo> listarCargos() throws Exception {
		TypedQuery<Cargo> cargoQuery = entityManager.createQuery("FROM Cargo c", Cargo.class);
		return cargoQuery.getResultList();
	}

}
