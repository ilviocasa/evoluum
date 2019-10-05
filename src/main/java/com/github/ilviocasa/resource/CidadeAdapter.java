package com.github.ilviocasa.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import external.ExtEstado;
import external.ExtMesorregiao;
import external.ExtMicrorregiao;
import external.ExtMunicipio;
import external.ExtRegiao;

public class CidadeAdapter {

	private ExtMunicipio municipio;

	public CidadeAdapter(ExtMunicipio municipio) {
		assertThat(municipio, is(notNullValue()));
		this.municipio = municipio;
	}
	
	public Cidade getCidade() {
		ExtMicrorregiao microrregiao = municipio.getMicrorregiao();
		ExtMesorregiao mesorregiao = microrregiao.getMesorregiao();
		ExtEstado estado = mesorregiao.getEstado();
		ExtRegiao regiao = estado.getRegiao();
		
		int idEstado = estado.getId();
		String siglaEstado = estado.getSigla();
		String regiaoNome = regiao.getNome();
		String nomeCidade = municipio.getNome();
		String nomeMesorregiao = mesorregiao.getNome();
		String uf = estado.getSigla();
		String nomeFormatado = String.format("%s/%s", nomeCidade, uf);
		
		return new Cidade(idEstado, 
							siglaEstado, 
							regiaoNome,
							nomeCidade, 
							nomeMesorregiao, 
							nomeFormatado);
	}
}
