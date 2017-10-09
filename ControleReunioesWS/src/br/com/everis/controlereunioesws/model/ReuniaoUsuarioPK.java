package br.com.everis.controlereunioesws.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ReuniaoUsuarioPK implements Serializable {

	private static final long serialVersionUID = -2256461991182558030L;

	@ManyToOne
	private Reuniao reuniao = null;
	
	@ManyToOne
	private Usuario usuario = null;

	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
