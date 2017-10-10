package br.com.everis.controlereunioesws.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioQualificacaoPK implements Serializable {

	private static final long serialVersionUID = 5520982782334240078L;
	
	@ManyToOne
	private Usuario usuario = null;
	
	@ManyToOne
	private Qualificacao qualificacao = null;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Qualificacao getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(Qualificacao qualificacao) {
		this.qualificacao = qualificacao;
	}

}
