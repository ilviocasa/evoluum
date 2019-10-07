package com.github.ilviocasa.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

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
 * The Municipio Base Ibge Web Client is responsible to call the IBGE API and 
 * returns a parsed response. As just names as needed to map, the class works 
 * with {@link ExtBase}.
 * 
 * @author silvio
 *
 */
@Component
public class MunicipioBaseIbgeWebClient {

	private static final Logger LOG = LoggerFactory.getLogger(MunicipioBaseIbgeWebClient.class);
	
	@Autowired
	private CacheManager cacheManager;
	
	@CachePut(value = "municipiosBaseCache", key = "#id")
	@HystrixCommand(fallbackMethod = "reliable")
	public ExtBase[] findAllById(int id) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		String resource = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + id + "/municipios";

		ResponseEntity<ExtBase[]> response = restTemplate.getForEntity(resource, ExtBase[].class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		ExtBase[] body = response.getBody();
		assertThat(body.length, is(not(0)));
		
		return body;
	}
	
	public ExtBase[] reliable(int id) {
		Cache cache = cacheManager.getCache("municipiosBaseCache");
		if (cache == null || cache.get(id) == null) {
			LOG.warn("Circuit breaker detected and no data in cache, data may be inconsistent");
			return new ExtBase[0];
		}
		
		LOG.warn("Circuit breaker detected");
		return cache.get(id, ExtBase[].class);
	}
}
