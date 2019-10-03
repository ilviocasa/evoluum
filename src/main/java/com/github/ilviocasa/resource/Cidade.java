package com.github.ilviocasa.resource;

/**
 * The Cidade Resource contains info about Cidade
 * 
 * @author silvio
 *
 */
public class Cidade {

	private Integer idEstado;
	private String siglaEstado;
	private String regiaoNome;
	private String nomeCidade;
	private String nomeMesorregiao;
	private String nomeFormatado;
	
	public Cidade() {
	}

	public Cidade(Integer idEstado, String siglaEstado, String regiaoNome, String nomeCidade, String nomeMesorregiao,
			String nomeFormatado) {
		super();
		this.idEstado = idEstado;
		this.siglaEstado = siglaEstado;
		this.regiaoNome = regiaoNome;
		this.nomeCidade = nomeCidade;
		this.nomeMesorregiao = nomeMesorregiao;
		this.nomeFormatado = nomeFormatado;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public String getRegiaoNome() {
		return regiaoNome;
	}

	public void setRegiaoNome(String regiaoNome) {
		this.regiaoNome = regiaoNome;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getNomeMesorregiao() {
		return nomeMesorregiao;
	}

	public void setNomeMesorregiao(String nomeMesorregiao) {
		this.nomeMesorregiao = nomeMesorregiao;
	}

	public String getNomeFormatado() {
		return nomeFormatado;
	}

	public void setNomeFormatado(String nomeFormatado) {
		this.nomeFormatado = nomeFormatado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstado == null) ? 0 : idEstado.hashCode());
		result = prime * result + ((nomeCidade == null) ? 0 : nomeCidade.hashCode());
		result = prime * result + ((nomeFormatado == null) ? 0 : nomeFormatado.hashCode());
		result = prime * result + ((nomeMesorregiao == null) ? 0 : nomeMesorregiao.hashCode());
		result = prime * result + ((regiaoNome == null) ? 0 : regiaoNome.hashCode());
		result = prime * result + ((siglaEstado == null) ? 0 : siglaEstado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (idEstado == null) {
			if (other.idEstado != null)
				return false;
		} else if (!idEstado.equals(other.idEstado))
			return false;
		if (nomeCidade == null) {
			if (other.nomeCidade != null)
				return false;
		} else if (!nomeCidade.equals(other.nomeCidade))
			return false;
		if (nomeFormatado == null) {
			if (other.nomeFormatado != null)
				return false;
		} else if (!nomeFormatado.equals(other.nomeFormatado))
			return false;
		if (nomeMesorregiao == null) {
			if (other.nomeMesorregiao != null)
				return false;
		} else if (!nomeMesorregiao.equals(other.nomeMesorregiao))
			return false;
		if (regiaoNome == null) {
			if (other.regiaoNome != null)
				return false;
		} else if (!regiaoNome.equals(other.regiaoNome))
			return false;
		if (siglaEstado == null) {
			if (other.siglaEstado != null)
				return false;
		} else if (!siglaEstado.equals(other.siglaEstado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [idEstado=" + idEstado + ", siglaEstado=" + siglaEstado + ", regiaoNome=" + regiaoNome
				+ ", nomeCidade=" + nomeCidade + ", nomeMesorregiao=" + nomeMesorregiao + ", nomeFormatado="
				+ nomeFormatado + "]";
	}

	
}
