package com.github.ilviocasa.core;

/**
 * The Swagger Strings makes all swagger text available. 
 * 
 * @author silvio
 *
 */
public class SwaggerStrings {

	public static final String TAG_CIDADES = "cidades";
	public static final String DESC_CONTROLLER_CIDADES = "Endpoint referente a cidades do Brasil.";
	public static final String DESC_CIDADES = "Lista todas as cidades do Brasil";
	public static final String DESC_CIDADES_BY_NAME = "Procura a cidade pelo nome";
	public static final String RESP_OK_CIDADES = "Retorna a lista em formato JSON. A lista pode estar vazia caso o serviços externos fiquem offline.";
	public static final String RESP_OK_CIDADES_CSV = "Retorna a lista em formato CSV. A lista pode estar vazia caso o serviços externos fiquem offline.";
	public static final String RESP_OK_CIDADES_BY_NAME = "Retorna o id referente a cidade";
	public static final String RESP_NOT_FOUND_CIDADES_BY_NAME = "O nome da cidade não se encontra";
	public static final String PARAM_NOME_CIDADE = "Nome da cidade";
}
