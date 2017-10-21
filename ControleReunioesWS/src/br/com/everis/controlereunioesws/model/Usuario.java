package br.com.everis.controlereunioesws.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	private static final long serialVersionUID = -4071555307243175347L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario = 0L;

	@Column(name = "usuario", length = 60)
	private String usuario = null;

	@Column(name = "senha", length = 100)
	private String senha = null;

	@Column(name = "nome_completo", nullable = false, length = 100)
	private String nomeCompleto = null;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email = null;

	@ManyToOne
	@JoinColumn(name = "id_cargo_FK")
	private Cargo cargo = null;

	@ManyToOne
	@JoinColumn(name = "id_permissao_FK")
	private Permissao permissao = null;

	@OneToMany(mappedBy = "pk.usuario", cascade = CascadeType.ALL)
	private List<ReuniaoUsuario> usuarioReuniao = null;

	@OneToMany(mappedBy = "pk.usuario", cascade = CascadeType.ALL)
	private List<UsuarioQualificacao> usuarioQualificacao = null;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public List<ReuniaoUsuario> getUsuarioReuniao() {
		return usuarioReuniao;
	}

	public void setUsuarioReuniao(List<ReuniaoUsuario> usuarioReuniao) {
		this.usuarioReuniao = usuarioReuniao;
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
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((usuarioQualificacao == null) ? 0 : usuarioQualificacao.hashCode());
		result = prime * result + ((usuarioReuniao == null) ? 0 : usuarioReuniao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (usuarioQualificacao == null) {
			if (other.usuarioQualificacao != null)
				return false;
		} else if (!usuarioQualificacao.equals(other.usuarioQualificacao))
			return false;
		if (usuarioReuniao == null) {
			if (other.usuarioReuniao != null)
				return false;
		} else if (!usuarioReuniao.equals(other.usuarioReuniao))
			return false;
		return true;
	}

}
