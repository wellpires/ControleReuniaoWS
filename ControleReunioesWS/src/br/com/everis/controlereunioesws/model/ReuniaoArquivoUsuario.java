package br.com.everis.controlereunioesws.model;

import java.util.List;

public class ReuniaoArquivoUsuario {
	
	private Reuniao reuniao = null;
	private List<Arquivo> listArquivos = null;
	private List<Usuario> listUsuarios = null;
	public Reuniao getReuniao() {
		return reuniao;
	}
	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}
	public List<Arquivo> getListArquivos() {
		return listArquivos;
	}
	public void setListArquivos(List<Arquivo> listArquivos) {
		this.listArquivos = listArquivos;
	}
	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}
	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

}
