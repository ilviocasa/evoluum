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

	public Cidade(Integer idEstado, String siglaEstado, String regiaoNome, String nomeCidade, String nomeMesorregiao,
			String nomeFormatado) {
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

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public String getRegiaoNome() {
		return regiaoNome;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public String getNomeMesorregiao() {
		return nomeMesorregiao;
	}

	public String getNomeFormatado() {
		return nomeFormatado;
	}

	@Override
	public String toString() {
		return "Cidade [idEstado=" + idEstado + ", siglaEstado=" + siglaEstado + ", regiaoNome=" + regiaoNome
				+ ", nomeCidade=" + nomeCidade + ", nomeMesorregiao=" + nomeMesorregiao + ", nomeFormatado="
				+ nomeFormatado + "]";
	}

}
