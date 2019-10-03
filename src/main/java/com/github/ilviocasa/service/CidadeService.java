package com.github.ilviocasa.service;

import org.springframework.stereotype.Service;

import com.github.ilviocasa.resource.Cidade;

/**
 * The Cidade Service is responsible to implements all routines about the Cidade Resource
 * 
 * @author silvio
 *
 */
@Service
public class CidadeService {

	public Cidade[] findAll() {
		Cidade[] cidades = new Cidade[1];
		Cidade cidade = new Cidade(1, "SC", "Sul", "Florianópolis", "Santa Caterina", "Florianópolis/SC");
		cidades[0] = cidade;
		
		return cidades;
	}

}
