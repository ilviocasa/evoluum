package com.github.ilviocasa.config;

import static com.github.ilviocasa.core.SwaggerStrings.TAG_CIDADES;
import static com.github.ilviocasa.core.SwaggerStrings.DESC_CONTROLLER_CIDADES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Swagger Config class is responsible to enable the Swagger module, which
 * provides a way to control the endpoints exposed by Swagger.
 * 
 * @author silvio
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.github.ilviocasa.controller"))
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build()
				.apiInfo(apiInfo())
				.tags(new Tag(TAG_CIDADES, DESC_CONTROLLER_CIDADES));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Evoluum REST API")
				.description("API referente aos municipios do Brasil.")
				.contact(new Contact("Silvio Casamassima", 
						"https://www.linkedin.com/in/silvio-casamassima-972b1459/",
						"casamassima.silvio@gmail.com"))
				.version("1.0.0")
				.build();
	}
}
