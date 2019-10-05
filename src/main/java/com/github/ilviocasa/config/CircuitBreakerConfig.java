package com.github.ilviocasa.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;



/**
 * The Circuit Breaker Config is responsible to enable the Circuit
 * breaker module, which provides a way to to gracefully degrade functionality 
 * when a method call fails. Use of the Circuit Breaker pattern can let a 
 * microservice continue operating when a related service fails, preventing the 
 * failure from cascading and giving the failing service time to recover.
 * 
 * @author silvio
 *
 */
@Configuration
@EnableCircuitBreaker
public class CircuitBreakerConfig {

	
}
