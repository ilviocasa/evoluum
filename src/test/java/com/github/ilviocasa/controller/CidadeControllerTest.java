package com.github.ilviocasa.controller;

import static com.github.ilviocasa.core.ApiLinksV1.CIDADES;
import static com.github.ilviocasa.core.ApiLinksV1.CIDADES_CSV;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.github.ilviocasa.AbstractSpringIntegration;


public class CidadeControllerTest extends AbstractSpringIntegration {

	@Autowired
	protected MockMvc mvc;
	
	@Test
	public void whenTryFindAll_ShouldReturnCidadeArray() throws Exception {	
		
	    mvc.perform(MockMvcRequestBuilders.get(CIDADES))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$").isArray())
	    	.andExpect(jsonPath("$.length()").isNumber());
	}
	
	@Test
	public void whenTryFindAllInCsvFormat_ShouldReturnCsv() throws Exception {	
		
	    mvc.perform(MockMvcRequestBuilders.get(CIDADES_CSV))
	    	.andExpect(status().isOk());
	}
	
	@Test
	public void whenTryFindACidade_ShouldReturnId() throws Exception {	
		
	    mvc.perform(MockMvcRequestBuilders.get(CIDADES + "/Brasília"))
	    	.andExpect(status().isOk());
	}
	
	@Test
	public void whenTryFindAWrongCidade_ShouldReturnNotFound() throws Exception {	
		
	    mvc.perform(MockMvcRequestBuilders.get(CIDADES + "/Brasilia"))
	    	.andExpect(status().isNotFound());
	}
}
