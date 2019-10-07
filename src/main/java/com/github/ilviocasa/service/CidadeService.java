package com.github.ilviocasa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ilviocasa.resource.Cidade;
import com.github.ilviocasa.resource.CidadeAdapter;

import exception.CidadeNotFoundException;
import external.ExtBase;
import external.ExtMunicipio;

/**
 * The Cidade Service is responsible to implements all lookup routines about cidade
 * Resource
 * 
 * @author silvio
 *
 */
@Service
public class CidadeService {

	private static final Logger LOG = LoggerFactory.getLogger(CidadeService.class);
	
	@Autowired
	private EstadoBaseIbgeWebClient estadoBaseIbgeWebClient;
	
	@Autowired
	private MunicipioBaseIbgeWebClient municipioBaseIbgeWebClient;
	
	@Autowired
	private MunicipioIbgeWebClient municipioIbgeWebClient;
	
	public List<Cidade> findAll() {		
		LOG.info("Try to find all cidades");
		List<Cidade> cidades = new ArrayList<>();
		
		ExtBase[] estados = estadoBaseIbgeWebClient.findAll();
		LOG.debug(String.format("Amount of estados found: %d", estados.length));
		
		for (ExtBase estado : estados) {
			int id = estado.getId();
			ExtMunicipio[] municipios = municipioIbgeWebClient.findAllById(id);
			LOG.debug(String.format("Amount of municipios found: %d", municipios.length));
			
			for (ExtMunicipio municipio : municipios) {
				CidadeAdapter adapter = new CidadeAdapter(municipio);
				Cidade cidade = adapter.getCidade();
				cidades.add(cidade);
				LOG.debug(String.format("Cidade added: %s", cidade));
			}
			LOG.info(String.format("Added %d cidades to the list by UF id: %d", municipios.length, id));
		}

		LOG.info(String.format("Total amount of cidades added to list: %d", cidades.size()));
		return cidades;
	}

	public int findFirst(String name) {
		LOG.info(String.format("Try to find a cidade by name '%s'", name));
		
		ExtBase[] estados = estadoBaseIbgeWebClient.findAll();
		LOG.debug(String.format("Amount of estados found: %d", estados.length));
		
		for (ExtBase estado : estados) {
			int id = estado.getId();
			ExtBase[] municipios = municipioBaseIbgeWebClient.findAllById(id);
			LOG.debug(String.format("Amount of municipios found: %d", municipios.length));
			
			for (ExtBase municipio : municipios) {
				if (name.equals(municipio.getNome())) {
					int idMunicipio = municipio.getId();
					LOG.info(String.format("The cidade '%s' has been found with id %d", name, idMunicipio));
					
					return idMunicipio;
				}
			}
			
		}
		
		String message = String.format("No cidade has been found by name '%s'", name);
		LOG.info(message);
		throw new CidadeNotFoundException(message);
	}
	
}
