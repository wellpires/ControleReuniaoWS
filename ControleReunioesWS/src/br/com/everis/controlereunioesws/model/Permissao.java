package br.com.everis.controlereunioesws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissoes")
public class Permissao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2969759883363859480L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permissao")
	private Long idPermissao = 0L;

	@Column(name = "permissao", nullable = false, length = 60)
	private String permissao = null;
	
	public Long getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(Long idPermissao) {
		this.idPermissao = idPermissao;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPermissao == null) ? 0 : idPermissao.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Permissao))
			return false;
		Permissao other = (Permissao) obj;
		if (idPermissao == null) {
			if (other.idPermissao != null)
				return false;
		} else if (!idPermissao.equals(other.idPermissao))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		return true;
	}

}
