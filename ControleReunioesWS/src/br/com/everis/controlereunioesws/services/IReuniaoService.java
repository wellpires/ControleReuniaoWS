package br.com.everis.controlereunioesws.services;

import java.util.List;

import br.com.everis.controlereunioesws.model.Reuniao;

public interface IReuniaoService {
	
	Reuniao gravarReuniao(Reuniao reuniao) throws Exception;

	void editarReuniao(Reuniao reuniao) throws Exception;
	
	void removerReuniao(Reuniao reuniao) throws Exception;
	
	Reuniao buscarReuniao (Reuniao reuniao) throws Exception;
	
	List<Reuniao> buscarReunioes(Reuniao reuniao) throws Exception;
	
	List<Reuniao> listarReunioes() throws Exception;

}
