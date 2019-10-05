package com.github.ilviocasa.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.ilviocasa.AbstractSpringIntegration;

public class CidadeCsvDataFormatTest extends AbstractSpringIntegration{

	@Test
	public void whenGetDataWithEmptyCidadeArray_ShouldReturnJustHeader() throws JsonProcessingException {
		List<Cidade> cidades = new ArrayList<Cidade>();
		CidadeCsvDataFormat dataFormat = new CidadeCsvDataFormat(cidades);
		
		byte[] data = dataFormat.getData();
		int toCompare = "idEstado,siglaEstado,regiaoNome,nomeCidade,nomeMesorregiao,nomeFormatado".getBytes().length;
		assertThat(data.length, is(lessThanOrEqualTo(toCompare)));
	}
}
