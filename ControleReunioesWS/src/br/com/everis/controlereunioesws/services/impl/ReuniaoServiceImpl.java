package br.com.everis.controlereunioesws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.everis.controlereunioesws.config.dao.JpaDao;
import br.com.everis.controlereunioesws.dao.IReuniaoDAO;
import br.com.everis.controlereunioesws.model.Reuniao;
import br.com.everis.controlereunioesws.services.IReuniaoService;

@Service
public class ReuniaoServiceImpl extends JpaDao<Integer, Reuniao> implements IReuniaoService {

	@Autowired
	private IReuniaoDAO reuniaoDAO = null;
	
	@Override
	public void salvarReuniao(Reuniao reuniao) throws Exception {
		reuniaoDAO.salvarReuniao(reuniao);
	}
	
	@Override
	public void editarReuniao(Reuniao reuniao) throws Exception {
		reuniaoDAO.editarReuniao(reuniao);
	}

	@Override
	public void removerReuniao(Reuniao reuniao) throws Exception {
		reuniaoDAO.removerReuniao(reuniao);
	}
	
	@Override
	public List<Reuniao> buscarReunioes(Reuniao reuniao) throws Exception {
		return reuniaoDAO.buscarReunioes(reuniao);
	}

	@Override
	public List<Reuniao> listarReunioes() throws Exception {
		return reuniaoDAO.listarReunioes();
	}

	@Override
	public Reuniao buscarReuniao(Reuniao reuniao) throws Exception {
		return reuniaoDAO.buscarReuniao(reuniao);
	}


}
