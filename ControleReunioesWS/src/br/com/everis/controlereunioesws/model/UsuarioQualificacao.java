package br.com.everis.controlereunioesws.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios_has_qualificacoes")
@AssociationOverrides({ @AssociationOverride(name = "pk.usuario", joinColumns = @JoinColumn(name = "id_usuario_FK")),
						@AssociationOverride(name = "pk.qualificacao", joinColumns = @JoinColumn(name = "id_qualificacao_FK")) })
public class UsuarioQualificacao implements Serializable {

	private static final long serialVersionUID = -8862559157115881582L;
	
	@EmbeddedId
	private UsuarioQualificacaoPK pk = new UsuarioQualificacaoPK();
	
	public UsuarioQualificacaoPK getPk() {
		return pk;
	}

	public void setPk(UsuarioQualificacaoPK pk) {
		this.pk = pk;
	}

}
