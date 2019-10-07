package com.github.ilviocasa.external;

/**
 * The External Estado Resource contains info about estado
 * 
 * @author silvio
 *
 */
public class ExtEstado extends ExtBase{

	private String sigla;
	private ExtRegiao regiao;
	
	public ExtEstado() {
		
	}


	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public ExtRegiao getRegiao() {
		return regiao;
	}


	public void setRegiao(ExtRegiao regiao) {
		this.regiao = regiao;
	}

	
	
	
}
