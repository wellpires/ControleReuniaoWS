package br.com.everis.controlereunioesws.services;

import java.util.Date;
import java.util.List;

import br.com.everis.controlereunioesws.model.Reuniao;

public interface IReuniaoService {
	
	void salvar(Reuniao reuniao) throws Exception;
	
	List<Reuniao> buscarReunioes(Date dtHoje) throws Exception;

}
