package com.github.ilviocasa.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import external.ExtBase;

/**
 * The Estado Base Ibge Web Client is responsible to call the IBGE API and returns a 
 * parsed response. As just IDs as needed to map, the class works with {@link ExtBase}.
 * 
 * @author silvio
 *
 */
@Component
public class EstadoBaseIbgeWebClient {

	private static final Logger LOG = LoggerFactory.getLogger(EstadoBaseIbgeWebClient.class);
	
	@HystrixCommand(fallbackMethod = "reliable")
	public List<ExtBase> findAll() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		String resource = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

		ResponseEntity<ExtBase[]> response = restTemplate.getForEntity(resource, ExtBase[].class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		ExtBase[] body = response.getBody();
		
		return new ArrayList<ExtBase>(Arrays.asList(body));
	}

	
	public List<ExtBase> reliable() {
		LOG.warn("Circuit breaker detected!");
		return new ArrayList<ExtBase>();
	}
}
