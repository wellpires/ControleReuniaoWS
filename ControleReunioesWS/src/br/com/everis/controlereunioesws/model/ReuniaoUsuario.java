package br.com.everis.controlereunioesws.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "reuniao_has_usuario")
@AssociationOverrides({
	@AssociationOverride(name = "pk.reuniao", joinColumns = @JoinColumn(name = "id_reuniao_FK")),
	@AssociationOverride(name = "pk.usuario", joinColumns = @JoinColumn(name = "id_usuario_FK"))})
public class ReuniaoUsuario implements Serializable {

	private static final long serialVersionUID = -6229209935235120905L;

	@EmbeddedId
	private ReuniaoUsuarioPK pk = new ReuniaoUsuarioPK();
	
	@Column(name = "confirmado", nullable = false, length = 1)
	private String confirmado = null;

	public ReuniaoUsuarioPK getPk() {
		return pk;
	}

	public void setPk(ReuniaoUsuarioPK pk) {
		this.pk = pk;
	}

	public String getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(String confirmado) {
		this.confirmado = confirmado;
	}

	public Reuniao getReuniao() {
		return getPk().getReuniao();
	}

	public void setReuniao(Reuniao reuniao) {
		getPk().setReuniao(reuniao);
	}

	public Usuario getUsuario() {
		return getPk().getUsuario();
	}

	public void setUsuario(Usuario usuario) {
		getPk().setUsuario(usuario);
	}
	
}
