package com.github.ilviocasa.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtMesorregiao extends ExtBase {

	@JsonProperty("UF")
	private ExtEstado estado;

	public ExtEstado getEstado() {
		return estado;
	}

	public void setEstado(ExtEstado estado) {
		this.estado = estado;
	}
	
	
}
