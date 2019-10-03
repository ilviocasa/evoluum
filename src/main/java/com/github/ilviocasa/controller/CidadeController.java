package com.github.ilviocasa.controller;

import static com.github.ilviocasa.core.ApiLinksV1.CIDADES;
import static com.github.ilviocasa.core.ApiLinksV1.CIDADES_CSV;
import static com.github.ilviocasa.core.SwaggerStrings.DESC_CIDADES;
import static com.github.ilviocasa.core.SwaggerStrings.DESC_CIDADES_BY_NAME;
import static com.github.ilviocasa.core.SwaggerStrings.PARAM_NOME_CIDADE;
import static com.github.ilviocasa.core.SwaggerStrings.RESP_NOT_FOUND_CIDADES_BY_NAME;
import static com.github.ilviocasa.core.SwaggerStrings.RESP_OK_CIDADES;
import static com.github.ilviocasa.core.SwaggerStrings.RESP_OK_CIDADES_BY_NAME;
import static com.github.ilviocasa.core.SwaggerStrings.RESP_OK_CIDADES_CSV;
import static com.github.ilviocasa.core.SwaggerStrings.TAG_CIDADES;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.ilviocasa.resource.Cidade;
import com.github.ilviocasa.resource.CidadeCsvDataFormat;
import com.github.ilviocasa.service.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;

/**
 * The Cidade Rest Controller is responsible to expose endpoints for
 * the cidade resource 
 * 
 * @author silvio
 *
 */
@RestController
@Api(tags = TAG_CIDADES)
public class CidadeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CidadeController.class);
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping(CIDADES)
	@ApiOperation(DESC_CIDADES)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = RESP_OK_CIDADES)
	})
    public Flux<Cidade> allJson() {
		
		LOG.info("GET - " + CIDADES);
		
    	Cidade[] cidades = cidadeService.findAll();
    	
        return Flux.just(cidades);
    }
	
	@GetMapping(value = CIDADES_CSV)
	@ApiOperation(DESC_CIDADES)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = RESP_OK_CIDADES_CSV)
	})
    public void allCSV(HttpServletResponse response) throws IOException {
		
		LOG.info("GET - " + CIDADES_CSV);
		response.setContentType("text/csv; charset=utf-8");		
		
		Cidade[] cidades = cidadeService.findAll();

		CidadeCsvDataFormat csvDataFormat = new CidadeCsvDataFormat(cidades);
		byte[] data = csvDataFormat.getData();
		
		byte[] buffer = new byte[1024 * 8];
		try (InputStream input = new ByteArrayInputStream(data); OutputStream output = response.getOutputStream();) {
			for (int length = 0; (length = input.read(buffer)) > 0;) {
				output.write(buffer, 0, length);
			}
		}		
		
    }

	@GetMapping(CIDADES + "/{nome_cidade}")
	@ApiOperation(DESC_CIDADES_BY_NAME)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = RESP_OK_CIDADES_BY_NAME),
	        @ApiResponse(code = 404, message = RESP_NOT_FOUND_CIDADES_BY_NAME)
	})
    public String findByName(@ApiParam(PARAM_NOME_CIDADE) @PathVariable("nome_cidade") String name) {
		
		LOG.info("GET - " + CIDADES + "/" + name);
		
        return "5";
    }
}
