package br.com.everis.controlereunioesws.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "arquivos")
public class Arquivo implements Serializable {

	private static final long serialVersionUID = 655852316146505529L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_arquivo")
	private Long idArquivo = 0L;
	
	@Column(name = "arquivo", nullable = false, length = 400)
	private String arquivo = null;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_reuniao_FK", referencedColumnName = "id_reuniao")
	private Reuniao reuniao = null;

	public Long getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Reuniao getReunioes() {
		return reuniao;
	}

	public void setReunioes(Reuniao reuniao) {
		this.reuniao = reuniao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivo == null) ? 0 : arquivo.hashCode());
		result = prime * result + ((idArquivo == null) ? 0 : idArquivo.hashCode());
		result = prime * result + ((reuniao == null) ? 0 : reuniao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Arquivo))
			return false;
		Arquivo other = (Arquivo) obj;
		if (arquivo == null) {
			if (other.arquivo != null)
				return false;
		} else if (!arquivo.equals(other.arquivo))
			return false;
		if (idArquivo == null) {
			if (other.idArquivo != null)
				return false;
		} else if (!idArquivo.equals(other.idArquivo))
			return false;
		if (reuniao == null) {
			if (other.reuniao != null)
				return false;
		} else if (!reuniao.equals(other.reuniao))
			return false;
		return true;
	}
	
}
