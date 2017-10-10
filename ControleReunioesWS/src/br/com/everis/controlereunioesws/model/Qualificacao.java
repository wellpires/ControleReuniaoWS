package br.com.everis.controlereunioesws.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "qualificacoes")
public class Qualificacao implements Serializable {

	private static final long serialVersionUID = -6154065907669775246L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_qualificacao")
	private Long idQualificacao = null;

	@Column(name = "qualificacao", nullable = false, length = 80)
	private String qualificacao = null;

	@Column(name = "instituicao", nullable = false, length = 80)
	private String instituicao = null;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.qualificacao", cascade = CascadeType.ALL)
	private List<UsuarioQualificacao> usuarioQualificacao = null;

	public Long getIdQualificacao() {
		return idQualificacao;
	}

	public void setIdQualificacao(Long idQualificacao) {
		this.idQualificacao = idQualificacao;
	}

	public String getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public List<UsuarioQualificacao> getUsuarioQualificacao() {
		return usuarioQualificacao;
	}

	public void setUsuarioQualificacao(List<UsuarioQualificacao> usuarioQualificacao) {
		this.usuarioQualificacao = usuarioQualificacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idQualificacao == null) ? 0 : idQualificacao.hashCode());
		result = prime * result + ((instituicao == null) ? 0 : instituicao.hashCode());
		result = prime * result + ((qualificacao == null) ? 0 : qualificacao.hashCode());
		result = prime * result + ((usuarioQualificacao == null) ? 0 : usuarioQualificacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Qualificacao))
			return false;
		Qualificacao other = (Qualificacao) obj;
		if (idQualificacao == null) {
			if (other.idQualificacao != null)
				return false;
		} else if (!idQualificacao.equals(other.idQualificacao))
			return false;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		if (qualificacao == null) {
			if (other.qualificacao != null)
				return false;
		} else if (!qualificacao.equals(other.qualificacao))
			return false;
		if (usuarioQualificacao == null) {
			if (other.usuarioQualificacao != null)
				return false;
		} else if (!usuarioQualificacao.equals(other.usuarioQualificacao))
			return false;
		return true;
	}

}
