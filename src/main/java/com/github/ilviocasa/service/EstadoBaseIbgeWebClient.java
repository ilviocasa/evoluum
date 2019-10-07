package com.github.ilviocasa.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.springframework.cache.interceptor.SimpleKey.EMPTY;

import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.ilviocasa.external.ExtBase;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
	
	@Autowired
	private CacheManager cacheManager;

	@CachePut("estadosCache")
	@HystrixCommand(fallbackMethod = "reliable")
	public ExtBase[] findAll() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		String resource = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

		ResponseEntity<ExtBase[]> response = restTemplate.getForEntity(resource, ExtBase[].class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		ExtBase[] body = response.getBody();
		assertThat(body.length, is(not(0)));
		
		return body;
	}
	
	public ExtBase[] reliable() {
		Cache cache = cacheManager.getCache("estadosCache");
		if (cache == null || cache.get(EMPTY) == null) {
			LOG.warn("Circuit breaker detected and no data in cache, data may be inconsistent");
			return new ExtBase[0];
		}
		
		LOG.warn("Circuit breaker detected");
		return cache.get(EMPTY, ExtBase[].class);
	}
}
