package br.com.everis.controlereunioesws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "reunioes")
public class Reuniao implements Serializable {

	private static final long serialVersionUID = 5476814367590068042L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reuniao")
	@Expose
	private Long idReuniao = 0L;

	@Column(name = "assunto", nullable = false, length = 60)
	@Expose
	private String assunto = null;

	@Column(name = "dt_inicio", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	private Date dtInicio = null;

	@Column(name = "dt_termino", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	private Date dtTermino = null;

//	@Column(name = "endereco", nullable = false, length = 150)
//	@Expose
//	private String endereco = null;

	@Column(name = "latitude", nullable = false, precision = 9, scale = 7)
	@Expose
	private Double latitude = null;
	
	@Column(name = "longitude", nullable = false, precision = 10, scale = 7)
	@Expose
	private Double longitude = null; 

	@Column(name = "sala", nullable = false, length = 60)
	@Expose
	private String sala = null;

	@Column(name = "pauta", nullable = false, length = 300)
	@Expose
	private String pauta = null;

	@OneToMany(mappedBy = "pk.reuniao", cascade = CascadeType.ALL)
	private List<ReuniaoUsuario> reuniaoUsuario;

	@OneToMany(mappedBy = "reuniao", cascade = CascadeType.ALL)
	private List<Arquivo> arquivos = null;

	public Reuniao() {
		// TODO Auto-generated constructor stub
	}
	
	public Reuniao(Long idReuniao) {
		super();
		this.idReuniao = idReuniao;
	}

	public Reuniao(Long idReuniao, String assunto, Date dtInicio, Date dtTermino, String endereco, String sala, String pauta) {
		super();
		this.idReuniao = idReuniao;
		this.assunto = assunto;
		this.dtInicio = dtInicio;
		this.dtTermino = dtTermino;
//		this.endereco = endereco;
		this.sala = sala;
		this.pauta = pauta;
	}

	public Long getIdReuniao() {
		return idReuniao;
	}

	public void setIdReuniao(Long idReuniao) {
		this.idReuniao = idReuniao;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtTermino() {
		return dtTermino;
	}

	public void setDtTermino(Date dtTermino) {
		this.dtTermino = dtTermino;
	}

//	public String getEndereco() {
//		return endereco;
//	}
//
//	public void setEndereco(String endereco) {
//		this.endereco = endereco;
//	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public List<ReuniaoUsuario> getReuniaoUsuario() {
		return reuniaoUsuario;
	}

	public void setReuniaoUsuario(List<ReuniaoUsuario> reuniaoUsuario) {
		this.reuniaoUsuario = reuniaoUsuario;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivo) {
		this.arquivos = arquivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((dtInicio == null) ? 0 : dtInicio.hashCode());
		result = prime * result + ((dtTermino == null) ? 0 : dtTermino.hashCode());
//		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((idReuniao == null) ? 0 : idReuniao.hashCode());
		result = prime * result + ((pauta == null) ? 0 : pauta.hashCode());
		result = prime * result + ((reuniaoUsuario == null) ? 0 : reuniaoUsuario.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		result = prime * result + ((arquivos == null) ? 0 : arquivos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reuniao))
			return false;
		Reuniao other = (Reuniao) obj;
		if (arquivos == null) {
			if (other.arquivos != null)
				return false;
		} else if (!arquivos.equals(other.arquivos))
			return false;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (dtInicio == null) {
			if (other.dtInicio != null)
				return false;
		} else if (!dtInicio.equals(other.dtInicio))
			return false;
		if (dtTermino == null) {
			if (other.dtTermino != null)
				return false;
		} else if (!dtTermino.equals(other.dtTermino))
			return false;
//		if (endereco == null) {
//			if (other.endereco != null)
//				return false;
//		} else if (!endereco.equals(other.endereco))
//			return false;
		if (idReuniao == null) {
			if (other.idReuniao != null)
				return false;
		} else if (!idReuniao.equals(other.idReuniao))
			return false;
		if (pauta == null) {
			if (other.pauta != null)
				return false;
		} else if (!pauta.equals(other.pauta))
			return false;
		if (reuniaoUsuario == null) {
			if (other.reuniaoUsuario != null)
				return false;
		} else if (!reuniaoUsuario.equals(other.reuniaoUsuario))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		return true;
	}

}
