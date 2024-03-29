package com.github.ilviocasa.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * The Cidade CSV Data Format is responsible to create a CSV raw representation of cidade array.
 * 
 * @author silvio
 *
 */
public class CidadeCsvDataFormat implements DataFormat {
	
	private static final char COMMA = ',';
	
	List<Cidade> cidades;
	
	public CidadeCsvDataFormat(List<Cidade> cidades) {
		assertThat(cidades, is(notNullValue()));
		this.cidades = cidades;
	}

	@Override
	public byte[] getData() throws JsonProcessingException {
		CsvMapper mapper = new CsvMapper();
		mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
		CsvSchema schema = mapper.schemaFor(Cidade.class);
		String data = mapper.writer(schema).writeValueAsString(cidades);
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(getHeader());
		
		if (!data.isEmpty()) {			
			builder.append(System.lineSeparator());
			builder.append(data);
		}
		
		return builder.toString().getBytes();
	}

	private String getHeader() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("idEstado");
		builder.append(COMMA);
		builder.append("siglaEstado");
		builder.append(COMMA);
		builder.append("regiaoNome");
		builder.append(COMMA);
		builder.append("nomeCidade");
		builder.append(COMMA);
		builder.append("nomeMesorregiao");
		builder.append(COMMA);
		builder.append("nomeFormatado");
		
		return builder.toString();
	}

	
}
